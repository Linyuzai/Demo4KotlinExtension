package com.linyuzai.kotlinextension

import android.content.Context
import com.linyuzai.kotlinextension.m.IShared
import com.linyuzai.kotlinextension.m.KShared

fun Context.bind() {
    KShared.bind(applicationContext)
}

fun Context.shared(): IShared {
    bind()
    return KShared
}
