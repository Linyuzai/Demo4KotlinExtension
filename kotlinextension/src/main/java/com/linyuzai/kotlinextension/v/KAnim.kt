package com.linyuzai.kotlinextension.v

import android.animation.Animator
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import com.linyuzai.kotlinextension.*

/**
 * Created by Administrator on 2017/5/12 0012.
 *
 */
@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
internal object KAnim : IAnim {
    override fun alpha(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animAlpha(to, from, duration)
        return this
    }

    override fun alpha(view: View, from: Float, to: Float, duration: Long, listener: Animator.AnimatorListener?): IAnim {
        view.animAlpha(to, from, duration, 0, listener)
        return this
    }

    override fun alpha(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animAlpha(to, from, duration, delay)
        return this
    }

    override fun scaleX(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animScaleX(to, from, duration)
        return this
    }

    override fun scaleX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animScaleX(to, from, duration, delay)
        return this
    }

    override fun scaleY(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animScaleY(to, from, duration)
        return this
    }

    override fun scaleY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animScaleY(to, from, duration, delay)
        return this
    }

    override fun translationX(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animTranslationX(to, from, duration)
        return this
    }

    override fun translationX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animTranslationX(to, from, duration, delay)
        return this
    }

    override fun translationY(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animTranslationY(to, from, duration)
        return this
    }

    override fun translationY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animTranslationY(to, from, duration, delay)
        return this
    }

    override fun rotation(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animRotation(to, from, duration)
        return this
    }

    override fun rotation(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animRotation(to, from, duration, delay)
        return this
    }

    override fun rotationX(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animRotationX(to, from, duration)
        return this
    }

    override fun rotationX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animRotationX(to, from, duration, delay)
        return this
    }

    override fun rotationY(view: View, from: Float, to: Float, duration: Long): IAnim {
        view.animRotationY(to, from, duration)
        return this
    }

    override fun rotationY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim {
        view.animRotationY(to, from, duration, delay)
        return this
    }
}

interface IAnim {
    fun alpha(view: View, from: Float, to: Float, duration: Long): IAnim

    fun alpha(view: View, from: Float, to: Float, duration: Long, listener: Animator.AnimatorListener? = null): IAnim

    fun alpha(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun scaleX(view: View, from: Float, to: Float, duration: Long): IAnim

    fun scaleX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun scaleY(view: View, from: Float, to: Float, duration: Long): IAnim

    fun scaleY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun translationX(view: View, from: Float, to: Float, duration: Long): IAnim

    fun translationX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun translationY(view: View, from: Float, to: Float, duration: Long): IAnim

    fun translationY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun rotation(view: View, from: Float, to: Float, duration: Long): IAnim

    fun rotation(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun rotationX(view: View, from: Float, to: Float, duration: Long): IAnim

    fun rotationX(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim

    fun rotationY(view: View, from: Float, to: Float, duration: Long): IAnim

    fun rotationY(view: View, from: Float, to: Float, duration: Long, delay: Long): IAnim
}