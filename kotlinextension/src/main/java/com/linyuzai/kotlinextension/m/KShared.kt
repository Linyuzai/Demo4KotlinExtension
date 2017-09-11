package com.linyuzai.kotlinextension.m

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.linyuzai.kotlinextension.Ex
import com.linyuzai.kotlinextension.deserialize
import com.linyuzai.kotlinextension.serialize

/**
 * Created by Administrator on 2017/5/2 0002.
 * @author linyuzai
 */
internal object KShared : IShared {
    private val PREFIX: String = "_"
    private val SIZE: String = "_size"
    private val prefs: SharedPreferences by lazy { Ex.context!!.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE) }

    @SuppressLint("CommitPrefEdits")
    override fun <T> put(key: String, value: T?): IShared =
            with(prefs.edit()) {
                when (value) {
                    is Long -> putLong(key, value)
                    is String -> putString(key, value)
                    is Int -> putInt(key, value)
                    is Boolean -> putBoolean(key, value)
                    is Float -> putFloat(key, value)
                    else -> putString(key, if (value == null) "" else value.serialize())
                }
                apply()
                this@KShared
            }

    override fun <T> putList(key: String, list: List<T>?): IShared {
        if (list == null) {
            put(key, "")
            return this
        }
        put("$PREFIX$key$SIZE", list.size)
        for (i in list.indices)
            put("$PREFIX$key$PREFIX$i", list[i])
        return this
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

    override fun <T> getList(key: String): List<T>? {
        val count: Int = get("$PREFIX$key$SIZE", 0)!!
        val list: MutableList<T> = arrayListOf()
        for (i in 0..count)
            list[i] = get<T>("$PREFIX$key$PREFIX$i")!!
        return list
    }

    override fun remove(key: String): IShared {
        prefs.edit().remove(key).apply()
        return this
    }

    override fun removeList(key: String): IShared {
        val count: Int = get("$PREFIX$key$SIZE", 0)!!
        for (i in 0..count)
            remove("$PREFIX$key$PREFIX$i")
        remove("$PREFIX$key$SIZE")
        return this
    }

    override fun clear(): IShared {
        prefs.edit().clear().apply()
        return this
    }
}

interface IShared {
    fun <T> put(key: String, value: T?): IShared

    fun <T> putList(key: String, list: List<T>?): IShared

    fun <T> get(key: String): T?

    fun <T> get(key: String, defValue: T?): T?

    fun <T> getList(key: String): List<T>?

    fun remove(key: String): IShared

    fun removeList(key: String): IShared

    fun clear(): IShared
}
