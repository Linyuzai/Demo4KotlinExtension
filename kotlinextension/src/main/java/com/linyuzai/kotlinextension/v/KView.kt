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
    override fun operator(): ViewOperator = pool().get(POOL_KEY)
}

class ViewOperator internal constructor() : PoolRecycler<ViewOperator> {

    private var views: Array<out View>? = null

    private var isNullSafe: Boolean = true

    fun views(vararg views: View): ViewOperator = apply { this.views = views }

    fun nullSafe(): ViewOperator = apply { this.isNullSafe = true }

    fun notNullSafe(): ViewOperator = apply { this.isNullSafe = false }

    fun show(): ViewOperator = apply {
        if (isNullSafe)
            views?.filter { !it.isVisible() }?.forEach { it.visible() }
        else
            views!!.filter { !it.isVisible() }.forEach { it.visible() }
    }

    fun hold(): ViewOperator = apply {
        if (isNullSafe)
            views?.filter { !it.isInvisible() }?.forEach { it.invisible() }
        else
            views!!.filter { !it.isInvisible() }.forEach { it.invisible() }
    }

    fun hide(): ViewOperator = apply {
        if (isNullSafe)
            views?.filter { !it.isGone() }?.forEach { it.gone() }
        else
            views!!.filter { !it.isGone() }.forEach { it.gone() }
    }

    override fun recycle() = pool().recycle(KView.POOL_KEY, reset())

    override fun reset(): ViewOperator = apply {
        views = null
        isNullSafe = true
    }
}

interface IView {
    fun operator(): ViewOperator
}