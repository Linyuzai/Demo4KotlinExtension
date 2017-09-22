package com.linyuzai.demo4kotlinextension

import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.linyuzai.kotlinextension.*

class MainActivity : Activity() {

    val button: Button by lazy {
        find<Button>(R.id.action_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = find(R.id.action_bar)
        Ex.bind(application)
                .configHandler()
                .configMemory()
                .configFile()
                .configResource()
        startActivity(Intent(this, MainActivity::class.java))
        intent(MainActivity::class)
        view().operator().views(button).nullSafe().show().recycle()
        //button.set
        //animAlpha(0.0f, 0.0f, 100, button)

        setStatusBarColorRes(R.color.colorAccent)
        setStatusBarTranslucent()

        shared().operator()

        shared().memory().shared().memory()

        var str: String? = null
        memory().operator().key("").value("").default(null).get<String?> { str = it }

        log().enable()

        anim().builder().with(button).onStart { }.onEnd { }.interpolator { a -> a.dec() }

        service().vibrator().vibrate(1000)

        file().operator().dir("").file("").create {}.rename()
    }
}
