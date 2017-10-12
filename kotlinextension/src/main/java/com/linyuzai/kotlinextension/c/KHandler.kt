@file:OpenApi

package com.linyuzai.kotlinextension.c

import android.os.Handler
import android.os.Looper
import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.u.PoolRecycler
import com.linyuzai.kotlinextension.pool
import java.util.*

/**
 * Created by linyuzai on 2017/5/12 0012.
 * @author linyuzai
 */
internal object KHandler : IHandler {
    internal val POOL_KEY: String = this::class.java.name
    internal val handler: Handler = Handler(Looper.getMainLooper())
    internal val runnableList: Vector<Runnable> = Vector()

    override fun access(): HandlerAccess = pool().get(POOL_KEY)
}

class HandlerAccess internal constructor() : PoolRecycler() {

    private var runnable: Runnable? = null

    private var delay: Long = 0

    fun runnable(runnable: Runnable): HandlerAccess = apply { this.runnable = runnable }

    fun runnable(runnable: () -> Unit): HandlerAccess = apply { this.runnable = Runnable { runnable.invoke() } }

    fun delay(delay: Long): HandlerAccess = apply { this.delay = delay }

    fun post(): HandlerAccess = apply { KHandler.handler.postDelayed(runnable, delay) }

    fun intercept(): HandlerAccess = apply { KHandler.handler.removeCallbacks(runnable) }

    //override fun recycle() = pool().recycle(KHandler.POOL_KEY, reset())

    override fun reset() {
        runnable = null
        delay = 0
    }
}

interface IHandler {
    fun access(): HandlerAccess
}