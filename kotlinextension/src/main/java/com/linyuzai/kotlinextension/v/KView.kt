package com.linyuzai.kotlinextension.v

import android.view.View
import com.linyuzai.kotlinextension.handler

/**
 * Created by Administrator on 2017/5/12 0012.
 *
 */
internal object KView : IView {
    override fun show(vararg views: View): IView {
        views.filter { it.visibility != View.VISIBLE }.forEach { it.visibility = View.VISIBLE }
        return this
    }

    override fun show(view: View, delay: Long): IView {
        handler().run(Runnable { if (view.visibility != View.VISIBLE) view.visibility = View.VISIBLE }, delay)
        return this
    }

    override fun hide(vararg views: View): IView {
        views.filter { it.visibility != View.GONE }.forEach { it.visibility = View.GONE }
        return this
    }

    override fun hide(view: View, delay: Long): IView {
        handler().run(Runnable { if (view.visibility != View.GONE) view.visibility = View.GONE }, delay)
        return this
    }

    override fun hold(vararg views: View): IView {
        views.filter { it.visibility != View.GONE }.forEach { it.visibility = View.GONE }
        return this
    }

    override fun hold(view: View, delay: Long): IView {
        handler().run(Runnable { if (view.visibility != View.INVISIBLE) view.visibility = View.INVISIBLE }, delay)
        return this
    }
}

interface IView {
    fun show(vararg views: View): IView

    fun show(view: View, delay: Long): IView

    fun hide(vararg views: View): IView

    fun hide(view: View, delay: Long): IView

    fun hold(vararg views: View): IView

    fun hold(view: View, delay: Long): IView
}