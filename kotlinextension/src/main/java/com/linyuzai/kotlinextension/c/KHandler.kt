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

    override fun run(runnable: Runnable): IHandler = apply {
        runnableList.add(runnable)
        handler.post(runnable)
    }

    override fun run(runnable: Runnable, delay: Long): IHandler = apply {
        runnableList.add(runnable)
        handler.postDelayed(runnable, delay)
    }

    override fun intercept(runnable: Runnable): IHandler = apply {
        runnableList.remove(runnable)
        handler.removeCallbacks(runnable)
    }

    override fun clear(): IHandler = apply {
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

    fun clear(): IHandler
}