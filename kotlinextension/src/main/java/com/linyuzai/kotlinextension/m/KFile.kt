@file:OpenApi

package com.linyuzai.kotlinextension.m

import com.linyuzai.kotlinextension.a.OpenApi
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler
import java.io.File

/**
 * Created by linyuzai on 2017/9/12.
 * @author linyuzai
 */
internal object KFile : IFile {
    internal val POOL_KEY: String = this::class.java.name
    override fun access(): FileAccess = pool().get(POOL_KEY)
}

class FileAccess internal constructor() : PoolRecycler() {

    private var file: String? = null

    private var dir: String? = null

    private var newName: String? = null

    fun file(file: String): FileAccess = apply { this.file = file }

    fun dir(dir: String): FileAccess = apply { this.dir = dir }

    fun newName(name: String): FileAccess = apply { this.newName = name }

    fun create(onCreate: ((file: File?) -> Unit)? = null): FileAccess = apply {
        val dir = File(this.dir)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(dir, this.file)
        val success = if (file.exists()) true else file.createNewFile()
        onCreate?.invoke(if (success) file else null)
    }

    fun rename(onRename: ((isSuccess: Boolean) -> Unit)? = null): FileAccess = apply {
        onRename?.invoke(File(dir, file).renameTo(File(dir, newName)))
    }

    //override fun recycle() = pool().recycle(KFile.POOL_KEY, reset())

    override fun reset() {
        file = null
        dir = null
    }
}

interface IFile {
    fun access(): FileAccess
}