package com.linyuzai.kotlinextension.v

import android.view.View

/**
 * Created by Administrator on 2017/5/12 0012.
 *
 */
internal object KView : IView {
    override fun show(vararg views: View): IView {
        views.filter { it.visibility != View.VISIBLE }.forEach { it.visibility = View.VISIBLE }
        return this
    }

    override fun hide(vararg views: View): IView {
        views.filter { it.visibility != View.GONE }.forEach { it.visibility = View.GONE }
        return this
    }
}

interface IView {
    fun show(vararg views: View): IView

    fun hide(vararg views: View): IView
}