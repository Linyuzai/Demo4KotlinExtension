package com.linyuzai.kotlinextension.store

import android.annotation.SuppressLint
import android.content.Context
import com.linyuzai.kotlinextension.deserialize
import com.linyuzai.kotlinextension.serialize

/**
 * Created by Administrator on 2017/5/2 0002.
 * @author linyuzai
 */
internal object Shared : IShared {
    internal var context: Context? = null
    private val prefs by lazy {
        context!!.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE)
    }

    @SuppressLint("CommitPrefEdits")
    override fun <T : Any> put(key: String, value: T?) =
            with(prefs.edit()) {
                when (value) {
                    is Long -> putLong(key, value)
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Float -> putFloat(key, value)
                    else -> putString(key, value?.serialize())
                }
                apply()
            }

    override fun <T> get(key: String): T? = get(key, null)

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String, defValue: T?): T? =
            with(prefs) {
                when (defValue) {
                    is Long -> getLong(key, defValue) as T
                    is String -> getString(key, defValue) as T
                    is Int -> getInt(key, defValue) as T
                    is Boolean -> getBoolean(key, defValue) as T
                    is Float -> getFloat(key, defValue) as T
                    else -> getString(key, null)?.deserialize<T>() ?: defValue
                }
            }

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }
}

interface IShared {
    fun <T : Any> put(key: String, value: T?)

    fun <T> get(key: String): T?

    fun <T> get(key: String, defValue: T?): T?

    fun remove(key: String)

    fun clear()
}
