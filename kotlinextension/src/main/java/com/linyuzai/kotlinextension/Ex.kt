package com.linyuzai.kotlinextension

import android.app.Application
import android.content.Context

/**
 * Created by linyuzai on 2017/9/8.
 */
object Ex {

    internal var context: Context? = null

    fun bind(app: Application) {
        this.context = app.applicationContext
    }

    fun unbind() {
        this.context = null
    }
}