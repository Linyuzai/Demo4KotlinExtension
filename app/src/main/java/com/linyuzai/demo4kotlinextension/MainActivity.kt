package com.linyuzai.demo4kotlinextension

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.linyuzai.kotlinextension.*

class MainActivity : AppCompatActivity() {

    val button: Button by lazy {
        find<Button>(R.id.action_bar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = find(R.id.action_bar)
        startActivity(Intent(this, MainActivity::class.java))
        intent(MainActivity::class)
        show(button, button, button)
        hide(button)
        scaleX(1.0f,0.0f,1000,1000)
    }
}
