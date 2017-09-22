@file:Suppress("UNCHECKED_CAST")

package com.linyuzai.kotlinextension

import android.app.Activity
import android.content.Intent
import android.view.View
import com.linyuzai.kotlinextension.v.*
import kotlin.reflect.KClass

//@JvmOverloads
fun Activity.setStatusBarColor(color: Int, alpha: Int = KStatusBar.DEFAULT_STATUS_BAR_ALPHA)
        = KStatusBar.setColor(this, color, alpha)

//@JvmOverloads
fun Activity.setStatusBarColorRes(color: Int, alpha: Int = KStatusBar.DEFAULT_STATUS_BAR_ALPHA)
        = KStatusBar.setColor(this, resources.getColor(color), alpha)

//@JvmOverloads
fun Activity.setStatusBarTranslucent(alpha: Int = 0) = KStatusBar.setTranslucent(this, alpha)

fun <T : View?> Activity.find(id: Int): T = findViewById(id) as T

fun Activity.intent(cls: KClass<out Activity>) = startActivity(Intent(this, cls.java))


