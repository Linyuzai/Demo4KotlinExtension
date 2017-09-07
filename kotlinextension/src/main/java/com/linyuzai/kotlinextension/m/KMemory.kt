package com.linyuzai.kotlinextension.m

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by Administrator on 2017/5/2 0002.
 * @author linyuzai
 */
@Suppress("UNCHECKED_CAST")
internal object KMemory : IMemory {
    private val map: ConcurrentHashMap<String, Any> = ConcurrentHashMap()
    override fun <T> put(key: String, value: T?): IMemory {
        map[key] = value as Any
        return this
    }

    override fun <T> get(key: String): T? = map[key] as T

    override fun <T> get(key: String, defValue: T?): T? =
            //map.getOrDefault(key, defValue as Any) as T
            //getOrDefault since jdk1.8
            get(key) ?: defValue
}

interface IMemory {
    fun <T> put(key: String, value: T?): IMemory

    fun <T> get(key: String): T?

    fun <T> get(key: String, defValue: T?): T?
}
