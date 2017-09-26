@file:OpenApi

package com.linyuzai.kotlinextension.m

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.linyuzai.kotlinextension.Ex
import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.a.RequestContext
import com.linyuzai.kotlinextension.deserialize
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.serialize
import com.linyuzai.kotlinextension.u.PoolRecycler

/**
 * Created by linyuzai on 2017/5/2 0002.
 * @author linyuzai
 */
internal object KShared : IShared {

    internal val POOL_KEY: String = this::class.java.name
    internal val PREFIX: String = "_"
    internal val SIZE: String = "_size"
    internal val prefs: SharedPreferences by lazy { Ex.context!!.getSharedPreferences("shared_preferences", Context.MODE_PRIVATE) }

    override fun operator(): SharedOperator = pool().get(POOL_KEY)

}

class SharedOperator internal constructor() : PoolRecycler<SharedOperator> {
    private var key: String? = null
    private var value: Any? = null
    private var default: Any? = null
    private var isListValue: Boolean = false
    private var isApply: Boolean = true

    fun key(key: String): SharedOperator = apply { this.key = key }

    fun value(value: Any?): SharedOperator = apply { this.value = value }

    fun default(default: Any?): SharedOperator = apply { this.default = default }

    fun listValue(): SharedOperator = apply { this.isListValue = true }

    fun sampleValue(): SharedOperator = apply { this.isListValue = false }

    fun useApply(): SharedOperator = apply { this.isApply = true }

    fun useCommit(): SharedOperator = apply { this.isApply = false }

    fun put(): SharedOperator = apply { put(key!!, value) }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(onGet: (value: T?) -> Unit): SharedOperator = apply {
        onGet.invoke(if (isListValue) getCollection<Any>(key!!) as T else get(key!!, default) as T)
    }

    fun remove(): SharedOperator = apply { if (isListValue) removeCollection(key!!) else remove(key!!) }

    @SuppressLint("CommitPrefEdits")
    private fun <T> put(key: String, value: T?) {
        with(KShared.prefs.edit()) {
            when (value) {
                is Long -> putLong(key, value)
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Array<*> -> putArray(key, value)
                is Collection<*> -> putCollection(key, value)
                else -> putString(key, if (value == null) "" else value.serialize())
            }
            if (isApply)
                apply()
            else
                commit()
        }
    }

    private fun <T> putCollection(key: String, collection: Collection<T>?) {
        if (collection == null) {
            put(key, "")
            return
        }
        put("${KShared.PREFIX}$key${KShared.SIZE}", collection.size)
        var index = 0
        collection.forEach {
            put("${KShared.PREFIX}$key${KShared.PREFIX}${index++}", it)
        }
    }

    private fun <T> putArray(key: String, array: Array<T>?) {
        if (array == null) {
            put(key, "")
            return
        }
        put("${KShared.PREFIX}$key${KShared.SIZE}", array.size)
        var index = 0
        for (i in array.indices) {
            put("${KShared.PREFIX}$key${KShared.PREFIX}$i", array[i])
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> get(key: String, defValue: T?): T? =
            with(KShared.prefs) {
                when (defValue) {
                    is Long -> getLong(key, defValue) as T
                    is String -> getString(key, defValue) as T
                    is Int -> getInt(key, defValue) as T
                    is Boolean -> getBoolean(key, defValue) as T
                    is Float -> getFloat(key, defValue) as T
                    else -> getString(key, null)?.deserialize<T>() ?: defValue
                }
            }

    @Suppress("UNCHECKED_CAST")
    private fun <T> getCollection(key: String): MutableList<T>? {
        val count: Int = get("${KShared.PREFIX}$key${KShared.SIZE}", 0)!!
        val list: MutableList<T> = arrayListOf()
        for (i in 0..count)
            list[i] = get("${KShared.PREFIX}$key${KShared.PREFIX}$i", default as T)!!
        return list
    }

    private fun remove(key: String) {
        KShared.prefs.edit().remove(key).apply()
    }

    private fun removeCollection(key: String) {
        val count: Int = get("${KShared.PREFIX}$key${KShared.SIZE}", 0)!!
        for (i in 0..count)
            remove("${KShared.PREFIX}$key${KShared.PREFIX}$i")
        remove("${KShared.PREFIX}$key${KShared.SIZE}")
    }

    @SuppressLint("CommitPrefEdits")
    fun clear(): SharedOperator = apply {
        if (isApply)
            KShared.prefs.edit().clear().apply()
        else
            KShared.prefs.edit().clear().commit()
    }

    override fun recycle() = pool().recycle(KShared.POOL_KEY, reset())

    override fun reset(): SharedOperator = apply {
        key = null
        value = null
        default = null
        isListValue = false
        isApply = true
    }
}

@RequestContext
interface IShared {

    fun operator(): SharedOperator
}
