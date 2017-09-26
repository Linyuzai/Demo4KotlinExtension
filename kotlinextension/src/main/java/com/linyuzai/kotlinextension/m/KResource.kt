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

    override fun operator(): ResourceOperator = pool().get(POOL_KEY)
}

class ResourceOperator internal constructor() : PoolRecycler<ResourceOperator> {
    private var resId: Int = 0
    private var theme: Resources.Theme? = null

    fun drawable(onGet: (drawable: Drawable) -> Unit): ResourceOperator = apply {
        onGet.invoke(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    KResource.res.getDrawable(resId, theme)
                else
                    KResource.res.getDrawable(resId))
    }

    fun color(onGet: (color: Int) -> Unit): ResourceOperator = apply {
        onGet.invoke(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    KResource.res.getColor(resId, theme)
                else
                    KResource.res.getColor(resId))
    }

    fun string(onGet: (string: String) -> Unit): ResourceOperator = apply { onGet.invoke(KResource.res.getString(resId)) }

    override fun recycle() = pool().recycle(KMemory.POOL_KEY, reset())

    override fun reset(): ResourceOperator = apply {
        resId = 0
        theme = null
    }
}

@RequestContext
interface IResource {
    fun operator(): ResourceOperator
}