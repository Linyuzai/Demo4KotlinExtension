package com.linyuzai.kotlinextension.v

import android.animation.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.linyuzai.kotlinextension.*

/**
 * Created by Administrator on 2017/5/12 0012.
 *
 */
@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
internal object KAnim : IAnim {

    override fun builder(): AnimBuilder = AnimBuilder()
}

class AnimBuilder internal constructor() {

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

    private var evaluator: TypeEvaluator<Any>? = null


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

    fun interpolator(interpolator: ((input: Float) -> Float)?): AnimBuilder =
            apply { if (interpolator != null) this.interpolator = TimeInterpolator { interpolator.invoke(it) } }

    fun interpolator(interpolator: TimeInterpolator): AnimBuilder = apply { this.interpolator = interpolator }

    fun evaluator(evaluator: ((fraction: Float, startValue: Any?, endValue: Any?) -> Any)?): AnimBuilder = apply {
        if (evaluator != null)
            this.evaluator = TypeEvaluator { fraction, startValue, endValue -> evaluator.invoke(fraction, startValue, endValue) }
    }

    @Suppress("UNCHECKED_CAST")
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun <T : Any> evaluator(evaluator: TypeEvaluator<T>): AnimBuilder = apply { this.evaluator = evaluator as TypeEvaluator<Any> }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun alpha(): ObjectAnimator = getAnim("alpha")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun scaleX(): ObjectAnimator = getAnim("scaleX")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun scaleY(): ObjectAnimator = getAnim("scaleY")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun translationX(): ObjectAnimator = getAnim("translationX")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun translationY(): ObjectAnimator = getAnim("translationY")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun rotation(): ObjectAnimator = getAnim("rotation")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun rotationX(): ObjectAnimator = getAnim("rotationX")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun rotationY(): ObjectAnimator = getAnim("rotationY")

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    private fun getAnim(anim: String): ObjectAnimator {
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