package com.linyuzai.kotlinextension.u

import android.util.Log

/**
 * Created by Administrator on 2017/5/16 0016.
 * @author linyuzai
 */
internal object KLog : ILog {
    @Volatile private var isEnable: Boolean = true

    override fun enable(): ILog = apply { isEnable = true }

    override fun disable(): ILog = apply { isEnable = false }

    override fun v(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.VERBOSE) }

    override fun v(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.VERBOSE) }

    override fun d(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.DEBUG) }

    override fun d(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.DEBUG) }

    override fun i(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.INFO) }

    override fun i(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.INFO) }

    override fun w(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.WARN) }

    override fun w(tag: String, tr: Throwable): ILog = apply { log1(tag, tr, Log.WARN) }

    override fun w(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.WARN) }

    override fun e(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.ERROR) }

    override fun e(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.ERROR) }

    override fun a(tag: String, msg: String): ILog = apply { log0(tag, msg, Log.ASSERT) }

    override fun a(tag: String, tr: Throwable): ILog = apply { log1(tag, tr, Log.ASSERT) }

    override fun a(tag: String, msg: String, tr: Throwable): ILog = apply { log2(tag, msg, tr, Log.ASSERT) }

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
