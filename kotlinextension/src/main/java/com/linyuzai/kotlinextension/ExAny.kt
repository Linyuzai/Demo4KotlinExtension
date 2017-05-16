package com.linyuzai.kotlinextension

import com.linyuzai.kotlinextension.c.KHandler
import com.linyuzai.kotlinextension.m.IMemory
import com.linyuzai.kotlinextension.m.KMemory
import com.linyuzai.kotlinextension.u.OnlyForAndroid
import com.linyuzai.kotlinextension.v.IAnim
import com.linyuzai.kotlinextension.v.IView
import com.linyuzai.kotlinextension.v.KAnim
import com.linyuzai.kotlinextension.v.KView
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

fun Any.view(): IView = KView

fun Any.anim(): IAnim = KAnim

@OnlyForAndroid
fun Any.run(runnable: Runnable) = KHandler.post(runnable)

@OnlyForAndroid
fun Any.run(name: String, runnable: Runnable) = KHandler.run(name, runnable)

