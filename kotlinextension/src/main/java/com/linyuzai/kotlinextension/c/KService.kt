package com.linyuzai.kotlinextension.c

import android.content.Context
import android.os.Vibrator
import com.linyuzai.kotlinextension.Ex

/**
 * Created by linyuzai on 2017/9/8.
 * @author linyuzai
 */
internal object KService : IService {
    override fun vibrator(): Vibrator = Ex.context!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

}

interface IService {
    fun vibrator(): Vibrator
}