package com.linyuzai.kotlinextension.c

import android.os.Handler
import android.os.Looper
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

    override fun operator(): HandlerOperator = pool().get(POOL_KEY)
}

class HandlerOperator internal constructor() : PoolRecycler<HandlerOperator> {

    var runnable: Runnable? = null

    var delay: Long = 0

    fun runnable(runnable: Runnable): HandlerOperator = apply { this.runnable = runnable }

    fun runnable(runnable: () -> Unit): HandlerOperator = apply { this.runnable = Runnable { runnable.invoke() } }

    fun delay(delay: Long): HandlerOperator = apply { this.delay = delay }

    fun post(): HandlerOperator = apply {
        KHandler.runnableList.add(runnable)
        KHandler.handler.postDelayed(runnable, delay)
    }

    fun intercept(): HandlerOperator = apply {
        KHandler.runnableList.remove(runnable)
        KHandler.handler.removeCallbacks(runnable)
    }

    fun clear(): HandlerOperator = apply {
        KHandler.runnableList.forEach {
            KHandler.handler.removeCallbacks(it)
        }
        KHandler.runnableList.clear()
    }

    override fun recycle(): HandlerOperator = apply { pool().recycle(KHandler.POOL_KEY, reset()) }

    override fun reset(): HandlerOperator = apply {
        runnable = null
        delay = 0
    }
}

interface IHandler {
    fun operator(): HandlerOperator
}