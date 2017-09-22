package com.linyuzai.kotlinextension

import android.app.Application
import android.content.Context
import com.linyuzai.kotlinextension.c.HandlerOperator
import com.linyuzai.kotlinextension.c.KHandler
import com.linyuzai.kotlinextension.m.*
import com.linyuzai.kotlinextension.v.AnimBuilder
import com.linyuzai.kotlinextension.v.KAnim
import com.linyuzai.kotlinextension.v.KView
import com.linyuzai.kotlinextension.v.ViewOperator

/**
 * Created by linyuzai on 2017/9/8.
 * @author linyuzai
 */
object Ex {

    internal var context: Context? = null

    fun autoConfig(app: Application): Ex = bind(app)
            .configHandler()
            .configFile()
            .configMemory()
            .configResource()
            .configAnim()
            .configView()

    fun bind(app: Application): Ex = apply { this.context = app.applicationContext }

    fun unbind(): Ex = apply { this.context = null }

    fun configHandler(): Ex = apply { pool().builder().tag(KHandler.POOL_KEY).from { HandlerOperator() }.build() }

    fun configFile(): Ex = apply { pool().builder().tag(KFile.POOL_KEY).from { FileOperator() }.build() }

    fun configMemory(): Ex = apply { pool().builder().tag(KMemory.POOL_KEY).from { MemoryOperator() }.build() }

    fun configResource(): Ex = apply { pool().builder().tag(KResource.POOL_KEY).from { ResourceOperator() }.build() }

    fun configAnim(): Ex = apply { pool().builder().tag(KAnim.POOL_KEY).from { AnimBuilder() }.build() }

    fun configView(): Ex = apply { pool().builder().tag(KView.POOL_KEY).from { ViewOperator() }.build() }
}