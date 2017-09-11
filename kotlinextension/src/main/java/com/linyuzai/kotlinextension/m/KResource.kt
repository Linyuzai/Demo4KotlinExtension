package com.linyuzai.kotlinextension.m

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.linyuzai.kotlinextension.Ex

/**
 * Created by linyuzai on 2017/8/29.
 * @author linyuzai
 */
internal object KResource : IResource {

    private val res: Resources by lazy { Ex.context!!.resources }

    override fun getDrawable(id: Int): Drawable = res.getDrawable(id)

    override fun getString(id: Int): String = res.getString(id)

    override fun getColor(id: Int): Int = res.getColor(id)
}

interface IResource {
    fun getDrawable(@DrawableRes id: Int): Drawable

    fun getString(@StringRes id: Int): String

    fun getColor(@ColorRes id: Int): Int
}