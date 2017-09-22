package com.linyuzai.kotlinextension

import android.view.Gravity
import android.widget.LinearLayout

/**
 * Created by linyuzai on 2017/9/20.
 * @author linyuzai
 */
fun LinearLayout.vertical() = { this.orientation = LinearLayout.VERTICAL }

fun LinearLayout.horizontal() = { this.orientation = LinearLayout.HORIZONTAL }

fun LinearLayout.gravityCenter() = { gravity = Gravity.CENTER }