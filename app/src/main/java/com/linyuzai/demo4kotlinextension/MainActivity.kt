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
        startActivity(Intent(this, MainActivity::class.java))
        intent(MainActivity::class)
        view().show(button, button).hide(button)
        anim().alpha(button, 0.0f, 0.0f, 100)
        //animAlpha(0.0f, 0.0f, 100, button)

        setStatusBarColorRes(R.color.colorAccent)
        setStatusBarTranslucent()

        shared().putList("key", arrayListOf<String>())

        shared().memory().shared().memory()

        log().enable()
    }
}
