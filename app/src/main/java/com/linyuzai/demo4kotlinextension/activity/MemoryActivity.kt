package com.linyuzai.demo4kotlinextension.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.linyuzai.demo4kotlinextension.R
import com.linyuzai.kotlinextension.find
import com.linyuzai.kotlinextension.memory

class MemoryActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.name

    private val mPutStringButton: Button by lazy { find<Button>(R.id.put_string) }

    private val mGetStringButton: Button by lazy { find<Button>(R.id.get_string) }

    private val mPutObjButton: Button by lazy { find<Button>(R.id.put_obj) }

    private val mGetObjButton: Button by lazy { find<Button>(R.id.get_obj) }

    private val mGetDefaultButton: Button by lazy { find<Button>(R.id.get_default) }

    private val mMemoryText: TextView by lazy { find<TextView>(R.id.memory_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)
        mPutStringButton.setOnClickListener { memory().access().key("put_string").value("This is String").put().recycle() }
        mGetStringButton.setOnClickListener { memory().access().key("put_string").get<String> { mMemoryText.text = it }.recycle() }
        mPutObjButton.setOnClickListener { memory().access().key("put_obj").value(Obj()).put().recycle() }
        mGetObjButton.setOnClickListener { memory().access().key("put_obj").get<Obj> { mMemoryText.text = it.toString() }.recycle() }
        mGetDefaultButton.setOnClickListener {
            memory().access().key("def").default("default value").get<String> { mMemoryText.text = it }.recycle()
        }
    }

    private class Obj
}
