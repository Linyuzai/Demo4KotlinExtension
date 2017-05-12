package com.linyuzai.kotlinextension

import com.linyuzai.kotlinextension.store.IMemory
import com.linyuzai.kotlinextension.store.Memory
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

fun Any.memory(): IMemory = Memory

