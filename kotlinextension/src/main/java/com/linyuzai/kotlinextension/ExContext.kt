package com.linyuzai.kotlinextension

import android.content.Context
import com.linyuzai.kotlinextension.m.IResource
import com.linyuzai.kotlinextension.m.IShared
import com.linyuzai.kotlinextension.m.KResource
import com.linyuzai.kotlinextension.m.KShared

fun Context.bind() {
    KShared.bind(applicationContext)
    KResource.bind(applicationContext)
}

fun Context.shared(): IShared {
    KShared.bind(applicationContext)
    return KShared
}

fun Context.res(): IResource {
    KResource.bind(applicationContext)
    return KResource
}
