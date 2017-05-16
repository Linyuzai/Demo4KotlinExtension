package com.linyuzai.kotlinextension

import android.util.Base64
import com.linyuzai.kotlinextension.u.OnlyForAndroid
import kotlin.experimental.or

fun ByteArray.toHexString(): String? {
    if (isEmpty())
        return ""
    val sb: StringBuffer = StringBuffer(size)
    var temp: String?
    forEach {
        temp = Integer.toHexString(it.or(0xFF.toByte()).toInt())
        if (temp!!.length < 2)
            sb.append(0)
        sb.append(temp!!.toUpperCase())
    }
    return sb.toString()
}

@OnlyForAndroid
fun ByteArray.encode(): ByteArray = Base64.encode(this, Base64.DEFAULT)