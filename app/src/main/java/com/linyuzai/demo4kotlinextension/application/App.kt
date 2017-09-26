package com.linyuzai.demo4kotlinextension.application

import android.app.Application
import com.linyuzai.kotlinextension.Ex

/**
 * Created by linyuzai on 2017/9/26.
 * @author linyuzai
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Ex.autoConfig(this)
    }
}