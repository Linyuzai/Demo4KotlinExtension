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

    override fun operator(): MemoryOperator = pool().get(POOL_KEY)
}

@Suppress("UNCHECKED_CAST")
class MemoryOperator internal constructor() : PoolRecycler<MemoryOperator>() {

    private var key: String? = null

    private var value: Any? = null

    private var default: Any? = null

    fun key(key: String): MemoryOperator = apply { this.key = key }

    fun <T : Any?> value(value: T?): MemoryOperator = apply { this.value = value }

    fun <T : Any?> default(default: T?): MemoryOperator = apply { this.default = default }

    fun put(): MemoryOperator = apply { KMemory.map[key!!] = value }

    //@JvmOverloads
    fun <T> get(onGet: (T?) -> Unit): MemoryOperator = apply { onGet.invoke((KMemory.map[key] ?: default) as T) }

    fun remove(): MemoryOperator = apply { KMemory.map.remove(key) }

    //override fun recycle() = pool().recycle(KMemory.POOL_KEY, reset())
    //getOrDefault since jdk1.8
    //map.getOrDefault(key, defValue as Any) as T

    override fun reset(): MemoryOperator = apply {
        key = null
        value = null
        default = null
    }
}

interface IMemory {
    fun operator(): MemoryOperator
}
