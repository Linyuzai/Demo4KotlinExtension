package com.linyuzai.kotlinextension

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animAlpha(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("alpha", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animScaleX(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("scaleX", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animScaleY(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("scaleY", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animTranslationX(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("translationX", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animTranslationY(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("translationY", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotation(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("rotation", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotationX(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("rotationX", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotationY(from: Float, to: Float, duration: Long, delay: Long = 0, listener: Animator.AnimatorListener? = null) {
    anim("rotationY", from, to, duration, delay, listener)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
private fun View.anim(anim: String, from: Float, to: Float, duration: Long, delay: Long, listener: Animator.AnimatorListener?) {
    val animator: ObjectAnimator = ObjectAnimator.ofFloat(this, anim, from, to).setDuration(duration)
    animator.startDelay = delay
    animator.addListener(listener)
    animator.start()
}
