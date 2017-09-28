package com.linyuzai.kotlinextension

import android.app.Application
import android.content.Context
import com.linyuzai.kotlinextension.c.HandlerAccess
import com.linyuzai.kotlinextension.c.KHandler
import com.linyuzai.kotlinextension.m.*
import com.linyuzai.kotlinextension.v.AnimAccess
import com.linyuzai.kotlinextension.v.KAnim
import com.linyuzai.kotlinextension.v.KView
import com.linyuzai.kotlinextension.v.ViewAccess

/**
 * Created by linyuzai on 2017/9/8.
 * @author linyuzai
 */
object Ex {

    internal var context: Context? = null

    fun autoConfig(app: Application) {
        bind(app)
        configHandler()
        configFile()
        configMemory()
        configResource()
        configShared()
        configAnim()
        configView()
    }

    fun bind(app: Application): Ex = apply { this.context = app.applicationContext }

    fun unbind(): Ex = apply { this.context = null }

    fun configHandler(): Ex = apply { pool().builder().tag(KHandler.POOL_KEY).from { HandlerAccess() }.build().recycle() }

    fun configFile(): Ex = apply { pool().builder().tag(KFile.POOL_KEY).from { FileAccess() }.build().recycle() }

    fun configMemory(): Ex = apply { pool().builder().tag(KMemory.POOL_KEY).from { MemoryAccess() }.build().recycle() }

    fun configResource(): Ex = apply { pool().builder().tag(KResource.POOL_KEY).from { ResourceAccess() }.build().recycle() }

    fun configShared(): Ex = apply { pool().builder().tag(KShared.POOL_KEY).from { SharedAccess() }.build().recycle() }

    fun configAnim(): Ex = apply { pool().builder().tag(KAnim.POOL_KEY).from { AnimAccess() }.build().recycle() }

    fun configView(): Ex = apply { pool().builder().tag(KView.POOL_KEY).from { ViewAccess() }.build().recycle() }
}