package com.linyuzai.kotlinextension.c

import android.os.Handler
import android.os.Looper
import android.os.Message
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Administrator on 2017/5/12 0012.
 * @author linyuzai
 */
internal object KHandler : Handler(Looper.getMainLooper()) {
    private val runnableMap: ConcurrentHashMap<String, Runnable> = ConcurrentHashMap()
    override fun handleMessage(msg: Message?) {

    }

    fun run(name: String, runnable: Runnable) = post(runnableMap.put(name, runnable))

    fun intercept(name: String) = removeCallbacks(runnableMap.remove(name))
}