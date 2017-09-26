@file:OpenApi

package com.linyuzai.kotlinextension.v

import android.animation.*
import android.os.Build
import android.view.View
import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler

/**
 * Created by linyuzai on 2017/5/12 0012.
 * @author linyuzai
 */

internal object KAnim : IAnim {
    internal val POOL_KEY: String = this::class.java.name
    override fun builder(): AnimBuilder = pool().get(POOL_KEY)
}

class AnimBuilder internal constructor() : PoolRecycler<AnimBuilder> {

    private var view: View? = null

    private var from: Float = 0f

    private var to: Float = 0f

    private var duration: Long = 0L

    private var delay: Long = 0L

    private var onStart: ((anim: Animator?) -> Unit)? = null

    private var onEnd: ((anim: Animator?) -> Unit)? = null

    private var onCancel: ((anim: Animator?) -> Unit)? = null

    private var onRepeat: ((anim: Animator?) -> Unit)? = null

    private var onPause: ((anim: Animator?) -> Unit)? = null

    private var onResume: ((anim: Animator?) -> Unit)? = null

    private var interpolator: TimeInterpolator? = null

    private var evaluator: TypeEvaluator<*>? = null


    fun with(view: View?): AnimBuilder = apply { this.view = view }

    fun from(from: Float): AnimBuilder = apply { this.from = from }

    fun to(to: Float): AnimBuilder = apply { this.to = to }

    fun duration(duration: Long): AnimBuilder = apply { this.duration = duration }

    fun delay(delay: Long): AnimBuilder = apply { this.delay = delay }

    fun onStart(onStart: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onStart = onStart }

    fun onEnd(onEnd: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onEnd = onEnd }

    fun onCancel(onCancel: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onCancel = onCancel }

    fun onRepeat(onRepeat: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onRepeat = onRepeat }

    fun onPause(onPause: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onPause = onPause }

    fun onResume(onResume: ((anim: Animator?) -> Unit)?): AnimBuilder = apply { this.onResume = onResume }

    fun interpolator(interpolator: (input: Float) -> Float): AnimBuilder =
            apply { this.interpolator = TimeInterpolator { interpolator.invoke(it) } }

    fun interpolator(interpolator: TimeInterpolator): AnimBuilder = apply { this.interpolator = interpolator }

    fun <T : Any> evaluator(evaluator: (fraction: Float, startValue: T?, endValue: T?) -> T): AnimBuilder = apply {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            throw RuntimeException("Api 11 at least")
        this.evaluator = TypeEvaluator<T> { fraction, startValue, endValue -> evaluator.invoke(fraction, startValue, endValue) }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> evaluator(evaluator: TypeEvaluator<T>): AnimBuilder = apply {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            throw RuntimeException("Api 11 at least")
        this.evaluator = evaluator as TypeEvaluator<Any>
    }

    fun alpha(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("alpha")) }

    fun scaleX(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("scaleX")) }

    fun scaleY(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("scaleY")) }

    fun translationX(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("translationX")) }

    fun translationY(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("translationY")) }

    fun rotation(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("rotation")) }

    fun rotationX(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("rotationX")) }

    fun rotationY(onGet: (anim: ObjectAnimator) -> Unit): AnimBuilder = apply { onGet.invoke(getAnim("rotationY")) }

    override fun recycle() = pool().recycle(KAnim.POOL_KEY, reset())

    override fun reset(): AnimBuilder = apply {
        view = null
        from = 0f
        to = 0f
        duration = 0L
        delay = 0L
        onStart = null
        onEnd = null
        onCancel = null
        onRepeat = null
        onPause = null
        onResume = null
        interpolator = null
        evaluator = null
    }

    private fun getAnim(anim: String): ObjectAnimator {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
            throw RuntimeException("Api 11 at least")
        val animator: ObjectAnimator = ObjectAnimator.ofFloat(view, anim, from, to).setDuration(duration)
        animator.startDelay = delay
        if (interpolator != null)
            animator.interpolator = interpolator
        if (evaluator != null)
            animator.setEvaluator(evaluator)
        val listener1: Animator.AnimatorListener = object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                onRepeat?.invoke(animation)
            }

            override fun onAnimationEnd(animation: Animator?) {
                onEnd?.invoke(animation)
            }

            override fun onAnimationCancel(animation: Animator?) {
                onCancel?.invoke(animation)
            }

            override fun onAnimationStart(animation: Animator?) {
                onStart?.invoke(animation)
            }
        }
        val listener2: Animator.AnimatorPauseListener = object : Animator.AnimatorPauseListener {
            override fun onAnimationPause(animation: Animator?) {
                onPause?.invoke(animation)
            }

            override fun onAnimationResume(animation: Animator?) {
                onResume?.invoke(animation)
            }

        }
        animator.addListener(listener1)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            animator.addPauseListener(listener2)
        }
        return animator
    }
}

interface IAnim {
    fun builder(): AnimBuilder
}