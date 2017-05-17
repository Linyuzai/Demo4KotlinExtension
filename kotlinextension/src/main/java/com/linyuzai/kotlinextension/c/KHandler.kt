package com.linyuzai.kotlinextension.c

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Administrator on 2017/5/12 0012.
 * @author linyuzai
 */
internal object KHandler : IHandler {
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val runnableMap: ConcurrentHashMap<String, Runnable> = ConcurrentHashMap()

    override fun run(runnable: Runnable): IHandler {
        handler.post(runnable)
        return this
    }

    override fun run(runnable: Runnable, delay: Long): IHandler {
        handler.postDelayed(runnable, delay)
        return this
    }

    override fun run(name: String, runnable: Runnable): IHandler {
        handler.post(runnableMap.put(name, runnable))
        return this
    }

    override fun run(name: String, runnable: Runnable, delay: Long): IHandler {
        handler.postDelayed(runnableMap.put(name, runnable), delay)
        return this
    }

    override fun intercept(name: String): IHandler {
        handler.removeCallbacks(runnableMap.remove(name))
        return this
    }
}

interface IHandler {
    fun run(runnable: Runnable): IHandler

    fun run(runnable: Runnable, delay: Long): IHandler

    fun run(name: String, runnable: Runnable): IHandler

    fun run(name: String, runnable: Runnable, delay: Long): IHandler

    fun intercept(name: String): IHandler
}