package com.linyuzai.kotlinextension

import android.view.View

fun View.visible() = run { visibility = View.VISIBLE }

fun View.invisible() = run { visibility = View.INVISIBLE }

fun View.gone() = run { visibility = View.GONE }

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun View.isGone(): Boolean = visibility == View.GONE








