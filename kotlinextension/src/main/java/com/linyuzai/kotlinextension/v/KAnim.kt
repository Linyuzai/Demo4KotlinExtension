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


    fun with(view: View?): AnimBuilder {
        this.view = view
        return this
    }

    fun from(from: Float): AnimBuilder {
        this.from = from
        return this
    }

    fun to(to: Float): AnimBuilder {
        this.to = to
        return this
    }

    fun duration(duration: Long): AnimBuilder {
        this.duration = duration
        return this
    }

    fun delay(delay: Long): AnimBuilder {
        this.delay = delay
        return this
    }

    fun onStart(onStart: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onStart = onStart
        return this
    }

    fun onEnd(onEnd: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onEnd = onEnd
        return this
    }

    fun onCancel(onCancel: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onCancel = onCancel
        return this
    }

    fun onRepeat(onRepeat: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onRepeat = onRepeat
        return this
    }

    fun onPause(onPause: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onPause = onPause
        return this
    }

    fun onResume(onResume: ((anim: Animator?) -> Unit)?): AnimBuilder {
        this.onResume = onResume
        return this
    }

    fun interpolator(interpolator: ((input: Float) -> Float)?): AnimBuilder {
        if (interpolator == null)
            return this
        this.interpolator = TimeInterpolator {
            interpolator.invoke(it)
        }
        return this
    }

    fun interpolator(interpolator: TimeInterpolator): AnimBuilder {
        this.interpolator = interpolator
        return this
    }

    fun evaluator(evaluator: ((fraction: Float, startValue: Any?, endValue: Any?) -> Any)?): AnimBuilder {
        if (evaluator == null)
            return this
        this.evaluator = TypeEvaluator { fraction, startValue, endValue -> evaluator.invoke(fraction, startValue, endValue) }
        return this
    }

    @Suppress("UNCHECKED_CAST")
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    fun <T : Any> evaluator(evaluator: TypeEvaluator<T>) {
        this.evaluator = evaluator as TypeEvaluator<Any>
    }

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