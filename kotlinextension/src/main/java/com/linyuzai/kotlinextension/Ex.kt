package com.linyuzai.kotlinextension

import android.content.Context

/**
 * Created by linyuzai on 2017/9/8.
 */
object Ex {

    internal var context: Context? = null

    fun bind(context: Context) {
        this.context = context
    }

    fun unbind() {
        this.context = null
    }
}