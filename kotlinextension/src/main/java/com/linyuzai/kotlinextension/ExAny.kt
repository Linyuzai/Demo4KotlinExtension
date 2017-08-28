package com.linyuzai.kotlinextension

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.annotation.RequiresApi
import android.view.View
import android.widget.LinearLayout
import com.linyuzai.kotlinextension.c.IHandler
import com.linyuzai.kotlinextension.c.KHandler
import com.linyuzai.kotlinextension.m.IMemory
import com.linyuzai.kotlinextension.m.IShared
import com.linyuzai.kotlinextension.m.KMemory
import com.linyuzai.kotlinextension.m.KShared
import com.linyuzai.kotlinextension.u.ILog
import com.linyuzai.kotlinextension.u.KLog
import com.linyuzai.kotlinextension.u.OnlyForAndroid
import com.linyuzai.kotlinextension.v.IAnim
import com.linyuzai.kotlinextension.v.IView
import com.linyuzai.kotlinextension.v.KAnim
import com.linyuzai.kotlinextension.v.KView
import junit.framework.Test
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.io.Serializable

@OnlyForAndroid
fun Any.serialize(): String? =
        if (this is Serializable) {
            var bos: ByteArrayOutputStream? = null
            var oos: ObjectOutputStream? = null
            try {
                bos = ByteArrayOutputStream()
                oos = ObjectOutputStream(bos)
                oos.writeObject(this)
                String(bos.toByteArray().encode())
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } finally {
                bos?.close()
                oos?.close()
            }
        } else {
            throw RuntimeException("Serializable must be implemented")
        }

fun Any.memory(): IMemory = KMemory

@OnlyForAndroid
fun Any.shared(): IShared = KShared

@OnlyForAndroid
fun Any.view(): IView = KView

@OnlyForAndroid
fun Any.anim(): IAnim = KAnim

@OnlyForAndroid
fun Any.log(): ILog = KLog

@OnlyForAndroid
fun Any.handler(): IHandler = KHandler

@OnlyForAndroid
fun Any.onClick(listener: View.OnClickListener, vararg views: View?) = views.forEach { it?.setOnClickListener(listener) }

@OnlyForAndroid
fun Any.onLongClick(listener: View.OnLongClickListener, vararg views: View?) = views.forEach { it?.setOnLongClickListener(listener) }
