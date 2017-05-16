@file:Suppress("UNCHECKED_CAST")

package com.linyuzai.kotlinextension

import android.util.Base64
import com.linyuzai.kotlinextension.u.OnlyForAndroid
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.StreamCorruptedException

@OnlyForAndroid
@Throws(IllegalArgumentException::class)
fun String.decode(): ByteArray = Base64.decode(this, Base64.DEFAULT)

@OnlyForAndroid
fun <T> String.deserialize(): T? {
    var bis: ByteArrayInputStream? = null
    var ois: ObjectInputStream? = null
    try {
        bis = ByteArrayInputStream(decode())
        ois = ObjectInputStream(bis)
        return ois.readObject() as T
    } catch (e1: IllegalArgumentException) {
        e1.printStackTrace()
    } catch (e2: IOException) {
        e2.printStackTrace()
    } catch (e3: StreamCorruptedException) {
        e3.printStackTrace()
    } finally {
        bis?.close()
        ois?.close()
    }
    return null
}