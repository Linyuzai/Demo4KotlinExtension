package com.linyuzai.kotlinextension

import com.linyuzai.kotlinextension.c.IHandler
import com.linyuzai.kotlinextension.c.IService
import com.linyuzai.kotlinextension.c.KHandler
import com.linyuzai.kotlinextension.c.KService
import com.linyuzai.kotlinextension.m.*
import com.linyuzai.kotlinextension.u.ILog
import com.linyuzai.kotlinextension.u.KLog
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

@OnlyForAndroid
fun Any.res(): IResource = KResource

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
fun Any.service(): IService = KService



