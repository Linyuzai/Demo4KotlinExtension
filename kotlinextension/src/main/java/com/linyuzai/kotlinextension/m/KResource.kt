package com.linyuzai.kotlinextension.m

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/**
 * Created by linyuzai on 2017/8/29.
 */
internal object KResource : IResource {

    private var res: Resources? = null
    fun bind(context: Context) {
        if (res == null) {
            synchronized(KResource::class.java) {
                if (res == null)
                    res = context.resources
            }
        }
    }

    override fun getDrawable(id: Int): Drawable? = res!!.getDrawable(id)

    override fun getString(id: Int): String? = res!!.getString(id)

    override fun getColor(id: Int): Int? = res!!.getColor(id)
}

interface IResource {
    fun getDrawable(@DrawableRes id: Int): Drawable?

    fun getString(@StringRes id: Int): String?

    fun getColor(@ColorRes id: Int): Int?
}