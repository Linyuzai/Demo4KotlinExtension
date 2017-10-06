@file:OpenApi

package com.linyuzai.kotlinextension.m

import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by liyuzai on 2017/5/2 0002.
 * @author linyuzai
 */
internal object KMemory : IMemory {
    internal val POOL_KEY: String = this::class.java.name
    internal val map: ConcurrentHashMap<String, Any?> = ConcurrentHashMap()

    override fun access(): MemoryAccess = pool().get(POOL_KEY)
}

@Suppress("UNCHECKED_CAST")
class MemoryAccess internal constructor() : PoolRecycler() {

    private var key: String? = null

    private var value: Any? = null

    private var default: Any? = null

    fun key(key: String): MemoryAccess = apply { this.key = key }

    fun <T : Any?> value(value: T?): MemoryAccess = apply { this.value = value }

    fun <T : Any?> default(default: T?): MemoryAccess = apply { this.default = default }

    fun put(): MemoryAccess = apply { KMemory.map[key!!] = value }

    //@JvmOverloads
    fun <T> get(onGet: (T?) -> Unit): MemoryAccess = apply { onGet.invoke((KMemory.map[key] ?: default) as T) }

    fun remove(): MemoryAccess = apply { KMemory.map.remove(key) }

    //override fun recycle() = pool().recycle(KMemory.POOL_KEY, reset())
    //getOrDefault since jdk1.8
    //map.getOrDefault(key, defValue as Any) as T

    override fun reset() {
        key = null
        value = null
        default = null
    }
}

interface IMemory {
    fun access(): MemoryAccess
}
