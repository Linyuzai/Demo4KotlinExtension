package com.linyuzai.demo4kotlinextension.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.linyuzai.demo4kotlinextension.R
import com.linyuzai.kotlinextension.find
import com.linyuzai.kotlinextension.handler

class HandlerActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.name

    private val mPostButton: Button by lazy { find<Button>(R.id.post) }

    private val mPostDelayButton: Button by lazy { find<Button>(R.id.post_delay) }

    private val mPostAndRemoveButton: Button by lazy { find<Button>(R.id.post_and_remove) }

    private val mPostAndRemoveAllButton: Button by lazy { find<Button>(R.id.post_and_remove_all) }

    private val mHandlerText: TextView by lazy { find<TextView>(R.id.handler_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)
        mPostButton.setOnClickListener { handler().access().runnable { mHandlerText.text = "post" }.post().recycle() }
        mPostDelayButton.setOnClickListener {
            handler().access().runnable { mHandlerText.text = "post delay" }.delay(2000).post().recycle()
        }
        mPostAndRemoveButton.setOnClickListener {
            handler().access().runnable { mHandlerText.text = "post and remove" }.delay(3000).post().intercept().recycle()
        }
        mPostAndRemoveAllButton.setOnClickListener {
            handler().access().runnable { mHandlerText.text = "post and remove all 1" }.post().recycle()
            handler().access().runnable { mHandlerText.text = "post and remove all 2" }.delay(3000).post().recycle()
            //handler().access().clear()
        }
    }
}
