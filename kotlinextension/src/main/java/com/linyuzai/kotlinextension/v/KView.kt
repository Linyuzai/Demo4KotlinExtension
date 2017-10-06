@file:OpenApi

package com.linyuzai.kotlinextension.v

import android.view.View
import com.linyuzai.kotlinextension.*
import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.u.PoolRecycler

/**
 * Created by linyuzai on 2017/5/12 0012.
 * @author linyuzai
 */
internal object KView : IView {
    internal val POOL_KEY: String = this::class.java.name
    override fun access(): ViewAccess = pool().get(POOL_KEY)
}

class ViewAccess internal constructor() : PoolRecycler() {

    private var views: Array<out View>? = null

    private var isNullSafe: Boolean = true

    fun views(vararg views: View): ViewAccess = apply { this.views = views }

    fun nullSafe(): ViewAccess = apply { this.isNullSafe = true }

    fun notNullSafe(): ViewAccess = apply { this.isNullSafe = false }

    fun show(): ViewAccess = apply {
        if (isNullSafe)
            views?.filter { !it.isVisible() }?.forEach { it.visible() }
        else
            views!!.filter { !it.isVisible() }.forEach { it.visible() }
    }

    fun hold(): ViewAccess = apply {
        if (isNullSafe)
            views?.filter { !it.isInvisible() }?.forEach { it.invisible() }
        else
            views!!.filter { !it.isInvisible() }.forEach { it.invisible() }
    }

    fun hide(): ViewAccess = apply {
        if (isNullSafe)
            views?.filter { !it.isGone() }?.forEach { it.gone() }
        else
            views!!.filter { !it.isGone() }.forEach { it.gone() }
    }

    //override fun recycle() = pool().recycle(KView.POOL_KEY, reset())

    override fun reset() {
        views = null
        isNullSafe = true
    }
}

interface IView {
    fun access(): ViewAccess
}