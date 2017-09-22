package com.linyuzai.kotlinextension

import android.widget.ImageView

/**
 * Created by linyuzai on 2017/9/20.
 * @author linyuzai
 */
fun ImageView.center() = { this.scaleType = ImageView.ScaleType.CENTER }

fun ImageView.centerCrop() = { this.scaleType = ImageView.ScaleType.CENTER_CROP }

fun ImageView.centerInside() = { this.scaleType = ImageView.ScaleType.CENTER_INSIDE }

fun ImageView.fitCenter() = { this.scaleType = ImageView.ScaleType.FIT_CENTER }

fun ImageView.fitXY() = { this.scaleType = ImageView.ScaleType.FIT_XY }

fun ImageView.fitStart() = { this.scaleType = ImageView.ScaleType.FIT_START }

fun ImageView.fitEnd() = { this.scaleType = ImageView.ScaleType.FIT_END }

fun ImageView.matrix() = { this.scaleType = ImageView.ScaleType.MATRIX }