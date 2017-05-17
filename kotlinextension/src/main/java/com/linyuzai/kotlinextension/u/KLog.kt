package com.linyuzai.kotlinextension.u

import android.util.Log

/**
 * Created by Administrator on 2017/5/16 0016.
 * @author linyuzai
 */
internal object KLog : ILog {
    @Volatile private var isEnable: Boolean = true

    override fun enable(): ILog {
        isEnable = true
        return this
    }

    override fun disable(): ILog {
        isEnable = false
        return this
    }

    override fun v(tag: String, msg: String): ILog {
        log0(tag, msg, Log.VERBOSE)
        return this
    }

    override fun v(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.VERBOSE)
        return this
    }

    override fun d(tag: String, msg: String): ILog {
        log0(tag, msg, Log.DEBUG)
        return this
    }

    override fun d(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.DEBUG)
        return this
    }

    override fun i(tag: String, msg: String): ILog {
        log0(tag, msg, Log.INFO)
        return this
    }

    override fun i(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.INFO)
        return this
    }

    override fun w(tag: String, msg: String): ILog {
        log0(tag, msg, Log.WARN)
        return this
    }

    override fun w(tag: String, tr: Throwable): ILog {
        log1(tag, tr, Log.WARN)
        return this
    }

    override fun w(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.WARN)
        return this
    }

    override fun e(tag: String, msg: String): ILog {
        log0(tag, msg, Log.ERROR)
        return this
    }

    override fun e(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.ERROR)
        return this
    }

    override fun a(tag: String, msg: String): ILog {
        log0(tag, msg, Log.ASSERT)
        return this
    }

    override fun a(tag: String, tr: Throwable): ILog {
        log1(tag, tr, Log.ASSERT)
        return this
    }

    override fun a(tag: String, msg: String, tr: Throwable): ILog {
        log2(tag, msg, tr, Log.ASSERT)
        return this
    }

    private fun log0(tag: String, msg: String, level: Int) {
        if (isEnable)
            when (level) {
                Log.VERBOSE -> Log.v(tag, msg)
                Log.DEBUG -> Log.d(tag, msg)
                Log.INFO -> Log.i(tag, msg)
                Log.WARN -> Log.w(tag, msg)
                Log.ERROR -> Log.e(tag, msg)
                Log.ASSERT -> Log.wtf(tag, msg)
            }
    }

    private fun log1(tag: String, tr: Throwable, level: Int) {
        if (isEnable)
            when (level) {
                Log.WARN -> Log.w(tag, tr)
                Log.ASSERT -> Log.wtf(tag, tr)
            }
    }

    private fun log2(tag: String, msg: String, tr: Throwable, level: Int) {
        if (isEnable)
            when (level) {
                Log.VERBOSE -> Log.v(tag, msg, tr)
                Log.DEBUG -> Log.d(tag, msg, tr)
                Log.INFO -> Log.i(tag, msg, tr)
                Log.WARN -> Log.w(tag, msg, tr)
                Log.ERROR -> Log.e(tag, msg, tr)
                Log.ASSERT -> Log.wtf(tag, msg, tr)
            }
    }
}

interface ILog {
    fun enable(): ILog

    fun disable(): ILog

    fun v(tag: String, msg: String): ILog

    fun v(tag: String, msg: String, tr: Throwable): ILog

    fun d(tag: String, msg: String): ILog

    fun d(tag: String, msg: String, tr: Throwable): ILog

    fun i(tag: String, msg: String): ILog

    fun i(tag: String, msg: String, tr: Throwable): ILog

    fun w(tag: String, msg: String): ILog

    fun w(tag: String, tr: Throwable): ILog

    fun w(tag: String, msg: String, tr: Throwable): ILog

    fun e(tag: String, msg: String): ILog

    fun e(tag: String, msg: String, tr: Throwable): ILog

    fun a(tag: String, msg: String): ILog

    fun a(tag: String, tr: Throwable): ILog

    fun a(tag: String, msg: String, tr: Throwable): ILog
}
