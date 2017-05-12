package com.linyuzai.kotlinextension

import android.content.Context
import com.linyuzai.kotlinextension.store.IShared
import com.linyuzai.kotlinextension.store.Shared

fun Context.shared(): IShared {
    Shared.context = applicationContext
    return Shared
}
