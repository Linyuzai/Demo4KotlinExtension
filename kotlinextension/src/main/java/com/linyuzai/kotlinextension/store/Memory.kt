package com.linyuzai.kotlinextension.store

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Administrator on 2017/5/2 0002.
 * @author linyuzai
 */
@Suppress("UNCHECKED_CAST")
internal object Memory : IMemory {
    private val map: ConcurrentHashMap<String, Any> = ConcurrentHashMap()
    override fun <T> put(key: String, value: T?) {
        map[key] = value as Any
    }

    override fun <T> get(key: String): T? = map[key] as T

    override fun <T> get(key: String, defValue: T?): T? = map.getOrDefault(key, defValue as Any) as T
}

interface IMemory {
    fun <T> put(key: String, value: T?)

    fun <T> get(key: String): T?

    fun <T> get(key: String, defValue: T?): T?
}
