package com.linyuzai.kotlinextension.u

import com.linyuzai.kotlinextension.pool
import java.util.*

/**
 * Created by linyuzai on 2017/9/17.
 * @author linyuzai
 */
internal object KPool : IPool {

    internal val POOL_KEY: String = this::class.java.name
    internal val poolMap: MutableMap<String, Queue<Any>> = hashMapOf()

    internal val creatorMap: MutableMap<String, (() -> Any)> = hashMapOf()

    init {
        PoolBuilder().tag(POOL_KEY).from { PoolBuilder() }.build().recycle()
    }

    override fun builder(): PoolBuilder = get(POOL_KEY)

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(tag: String): T {
        synchronized(poolMap) {
            if (poolMap.contains(tag)) {
                return (poolMap[tag]!!.poll() ?: creatorMap[tag]!!.invoke()) as T
            } else throw RuntimeException("use builder to build before get")
        }
    }

    override fun recycle(tag: String, item: Any) {
        synchronized(poolMap) {
            if (poolMap.contains(tag))
                poolMap[tag]!!.offer(item)
            else throw RuntimeException("no this tag found in pools")
        }
    }
}

class PoolBuilder internal constructor() : PoolRecycler<PoolBuilder> {

    private var tag: String? = null

    private var from: (() -> Any)? = null

    fun tag(tag: String): PoolBuilder = apply { this.tag = tag }

    fun from(from: (() -> Any)?): PoolBuilder = apply { this.from = from }

    fun build(): PoolBuilder = apply {
        synchronized(KPool.poolMap) {
            if (KPool.poolMap.contains(tag)) throw RuntimeException("this tag of pool has been build")
            else {
                KPool.poolMap.put(tag!!, LinkedList())
                KPool.creatorMap.put(tag!!, from!!)
            }
        }
    }

    override fun recycle(): PoolBuilder = apply { pool().recycle(KPool.POOL_KEY, reset()) }

    override fun reset(): PoolBuilder = apply {
        tag = null
        from = null
    }
}

interface IPool {
    fun builder(): PoolBuilder

    fun <T> get(tag: String): T

    fun recycle(tag: String, item: Any)
}

interface PoolRecycler<out T> {
    fun recycle(): T

    fun reset(): T
}
