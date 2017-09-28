@file:OpenApi

package com.linyuzai.kotlinextension.u

import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.pool
import java.util.*

/**
 * Created by linyuzai on 2017/9/17.
 * @author linyuzai
 */
internal object KPool : IPool {
    /**
     * the tag of pool in pool
     */
    internal val POOL_KEY: String = this::class.java.name
    /**
     * a map to store the instances
     */
    internal val poolMap: MutableMap<String, Queue<Any>> = hashMapOf()
    /**
     * a map to store the creator of instance
     */
    internal val creatorMap: MutableMap<String, (() -> Any)> = hashMapOf()

    /**
     * init the pool builder
     */
    init {
        PoolBuilder().tag(POOL_KEY).from { PoolBuilder() }.build().recycle()
    }

    override fun builder(): PoolBuilder = get(POOL_KEY)

    @Suppress("UNCHECKED_CAST")
    override fun <T : PoolRecycler<T>> get(tag: String): T {
        synchronized(poolMap) {
            if (poolMap.contains(tag)) {
                return (poolMap[tag]!!.poll() ?: creatorMap[tag]!!.invoke().apply { (this as T).poolTag = tag }) as T
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

/**
 * the builder to build a pool in pool
 */
class PoolBuilder internal constructor() : PoolRecycler<PoolBuilder>() {
    /**
     * the tag to get a instance
     */
    private var tag: String? = null
    /**
     * the creator of a instance
     */
    private var from: (() -> Any)? = null

    /**
     * set the tag
     */
    fun tag(tag: String): PoolBuilder = apply { this.tag = tag }

    /**
     * set the creator
     */
    fun from(from: (() -> Any)?): PoolBuilder = apply { this.from = from }

    /**
     * get the pool builder
     */
    fun build(): PoolBuilder = apply {
        synchronized(KPool.poolMap) {
            if (KPool.poolMap.contains(tag)) throw RuntimeException("this tag of pool has been build")
            else {
                KPool.poolMap.put(tag!!, LinkedList())
                KPool.creatorMap.put(tag!!, from!!)
            }
        }
    }

    override fun recycle() = pool().recycle(KPool.POOL_KEY, reset())

    override fun reset(): PoolBuilder = apply {
        tag = null
        from = null
    }
}

/**
 * The interface of KPool
 * @see KPool
 */
interface IPool {

    /**
     * get a pool builder from pool
     * @see KPool.builder
     */
    fun builder(): PoolBuilder

    /**
     * get a instance from pool by tag
     * @see KPool.get
     */
    fun <T : PoolRecycler<T>> get(tag: String): T

    /**
     * recycle a instance into pool by tag
     * @see KPool.recycle
     */
    fun recycle(tag: String, item: Any)
}

/**
 * Pool recycler is used to recycle for pool
 */
abstract class PoolRecycler<out T> {
    internal var poolTag: String? = null

    /**
     * The object extends this would be recycled
     * @see KPool.recycle
     */
    open fun recycle() = pool().recycle(poolTag!!, reset() as Any)

    /**
     * Reset the Object before recycled
     */
    abstract fun reset(): T
}
