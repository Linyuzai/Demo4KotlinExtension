package com.linyuzai.kotlinextension.c

import android.os.Handler
import android.os.Looper
import java.util.*
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Administrator on 2017/5/12 0012.
 * @author linyuzai
 */
internal object KHandler : IHandler {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnableList: Vector<Runnable> = Vector()

    override fun run(runnable: Runnable): IHandler {
        runnableList.add(runnable)
        handler.post(runnable)
        return this
    }

    override fun run(runnable: Runnable, delay: Long): IHandler {
        runnableList.add(runnable)
        handler.postDelayed(runnable, delay)
        return this
    }

    override fun intercept(runnable: Runnable): IHandler {
        runnableList.remove(runnable)
        handler.removeCallbacks(runnable)
        return this
    }

    override fun clear() {
        runnableList.forEach {
            handler.removeCallbacks(it)
        }
        runnableList.clear()
    }
}

interface IHandler {
    fun run(runnable: Runnable): IHandler

    fun run(runnable: Runnable, delay: Long): IHandler

    fun intercept(runnable: Runnable): IHandler

    fun clear()
}