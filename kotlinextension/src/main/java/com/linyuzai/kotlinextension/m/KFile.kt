package com.linyuzai.kotlinextension.m

import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler
import java.io.File

/**
 * Created by linyuzai on 2017/9/12.
 * @author linyuzai
 */
internal object KFile : IFile {
    internal val POOL_KEY: String = this::class.java.name
    override fun operator(): FileOperator = pool().get(POOL_KEY)
}

class FileOperator internal constructor() : PoolRecycler<FileOperator> {

    private var file: String? = null

    private var dir: String? = null

    private var newName: String? = null

    fun file(file: String): FileOperator = apply { this.file = file }

    fun dir(dir: String): FileOperator = apply { this.dir = dir }

    fun newName(name: String): FileOperator = apply { this.newName = name }

    fun create(onCreate: ((file: File?) -> Unit)? = null): FileOperator = apply {
        val dir = File(this.dir)
        if (!dir.exists())
            dir.mkdirs()
        val file = File(dir, this.file)
        val success = if (file.exists()) true else file.createNewFile()
        onCreate?.invoke(if (success) file else null)
    }

    fun rename(onRename: ((isSuccess: Boolean) -> Unit)? = null): FileOperator = apply {
        onRename?.invoke(File(dir, file).renameTo(File(dir, newName)))
    }

    override fun recycle(): FileOperator = apply { pool().recycle(KFile.POOL_KEY, reset()) }

    override fun reset(): FileOperator = apply {
        file = null
        dir = null
    }
}

interface IFile {
    fun operator(): FileOperator
}