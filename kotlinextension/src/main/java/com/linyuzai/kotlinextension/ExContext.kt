package com.linyuzai.kotlinextension

import android.content.Context
import com.linyuzai.kotlinextension.m.IShared
import com.linyuzai.kotlinextension.m.KShared

fun Context.shared(): IShared {
    KShared.context = applicationContext
    return KShared
}
