package com.linyuzai.kotlinextension

import android.animation.ObjectAnimator
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animAlpha(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "alpha", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animScaleX(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "scaleX", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animScaleY(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "scaleY", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animTranslationX(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "translationX", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animTranslationY(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "translationY", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotation(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "rotation", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotationX(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "rotationX", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
@JvmOverloads fun View.animRotationY(from: Float, to: Float, duration: Long, delay: Long = 0) {
    anim(this, "rotationY", from, to, duration, delay)
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
private fun View.anim(view: View, anim: String, from: Float, to: Float, duration: Long, delay: Long) {
    val animator: ObjectAnimator = ObjectAnimator.ofFloat(view, anim, from, to).setDuration(duration)
    animator.startDelay = delay
    animator.start()
}
