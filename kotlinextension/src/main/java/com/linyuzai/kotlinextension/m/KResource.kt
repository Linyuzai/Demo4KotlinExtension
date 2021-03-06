@file:OpenApi

package com.linyuzai.kotlinextension.m

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.os.Build
import com.linyuzai.kotlinextension.Ex
import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.a.RequestContext
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler

/**
 * Created by linyuzai on 2017/8/29.
 * @author linyuzai
 */
internal object KResource : IResource {

    internal val POOL_KEY: String = this::class.java.name

    internal val res: Resources by lazy { Ex.context!!.resources }

    override fun access(): ResourceAccess = pool().get(POOL_KEY)
}

class ResourceAccess internal constructor() : PoolRecycler() {
    private var resId: Int = 0
    private var theme: Resources.Theme? = null

    fun resId(id: Int): ResourceAccess = apply { this.resId = id }

    fun drawable(onGet: (drawable: Drawable) -> Unit): ResourceAccess = apply {
        onGet.invoke(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    KResource.res.getDrawable(resId, theme)
                else
                    KResource.res.getDrawable(resId))
    }

    fun mipmap(onGet: (drawable: Drawable) -> Unit): ResourceAccess = drawable(onGet)

    fun color(onGet: (color: Int) -> Unit): ResourceAccess = apply {
        onGet.invoke(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    KResource.res.getColor(resId, theme)
                else
                    KResource.res.getColor(resId))
    }

    fun string(onGet: (string: String) -> Unit): ResourceAccess = apply { onGet.invoke(KResource.res.getString(resId)) }

    //override fun recycle() = pool().recycle(KMemory.POOL_KEY, reset())

    override fun reset() {
        resId = 0
        theme = null
    }
}

@RequestContext
interface IResource {
    fun access(): ResourceAccess
}