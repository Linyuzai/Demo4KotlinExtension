@file:Suppress("UNCHECKED_CAST")

package com.linyuzai.kotlinextension

import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.support.annotation.IdRes
import android.support.annotation.RequiresApi
import android.view.View
import kotlin.reflect.KClass

fun <T> Activity.find(@IdRes id: Int): T = findViewById(id) as T

fun Activity.intent(cls: KClass<out Activity>) = startActivity(Intent(this, cls.java))

fun Activity.show(vararg views: View) = views.filter { it.visibility != View.VISIBLE }.forEach { it.visibility = View.VISIBLE }

fun Activity.hide(vararg views: View) = views.filter { it.visibility != View.GONE }.forEach { it.visibility = View.GONE }

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
fun Activity.alphaAnimator(from: Float, to: Float, duration: Long, delay: Long, vararg views: View) = views.forEach {
    val animator: ObjectAnimator = ObjectAnimator.ofFloat(it, "alpha", from, to).setDuration(duration)
    animator.startDelay = delay
    animator.start()
}

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
fun Activity.alpha(from: Float, to: Float, duration: Long, vararg views: View) = views.forEach {
    ObjectAnimator.ofFloat(it, "alpha", from, to).setDuration(duration).start()
}

fun Activity.scaleX(from: Float, to: Float, duration: Long, delay: Long, vararg views: View) = views.forEach {
    //ObjectAnimator.ofFloat()
}
