package com.linyuzai.demo4kotlinextension.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.linyuzai.demo4kotlinextension.R
import com.linyuzai.kotlinextension.find
import com.linyuzai.kotlinextension.pool
import com.linyuzai.kotlinextension.u.PoolRecycler

class PoolActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.name

    private val mAddPoolButton: Button by lazy { find<Button>(R.id.add_pool) }

    private val mGetPoolAndRecycleButton: Button by lazy { find<Button>(R.id.get_pool_and_recycle) }

    private val mGetPoolNoRecycleButton: Button by lazy { find<Button>(R.id.get_pool_no_recycle) }

    private val mPoolText: TextView by lazy { find<TextView>(R.id.pool_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pool)
        mAddPoolButton.setOnClickListener { pool().builder().tag(TAG).from { Builder() }.build().recycle() }
        mGetPoolAndRecycleButton.setOnClickListener {
            val builder: Builder = pool().get(TAG)
            mPoolText.text = builder.toString()
            builder.recycle()
        }
        mGetPoolNoRecycleButton.setOnClickListener {
            val builder: Builder = pool().get(TAG)
            mPoolText.text = builder.toString()
        }
    }

    private inner class Builder : PoolRecycler<Builder> {
        override fun recycle() = pool().recycle(TAG, this)

        override fun reset(): Builder = apply {}
    }
}
