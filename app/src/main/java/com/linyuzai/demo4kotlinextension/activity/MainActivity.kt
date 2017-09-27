package com.linyuzai.demo4kotlinextension.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.linyuzai.demo4kotlinextension.R
import com.linyuzai.kotlinextension.*

class MainActivity : AppCompatActivity() {

    private val mHandlerButton: Button by lazy { find<Button>(R.id.handler) }

    private val mIntentButton: Button by lazy { find<Button>(R.id.intent) }

    private val mPermissionButton: Button by lazy { find<Button>(R.id.permission) }

    private val mServiceButton: Button by lazy { find<Button>(R.id.service) }

    private val mFileButton: Button by lazy { find<Button>(R.id.file) }

    private val mMemoryButton: Button by lazy { find<Button>(R.id.memory) }

    private val mResourceButton: Button by lazy { find<Button>(R.id.resource) }

    private val mSharedButton: Button by lazy { find<Button>(R.id.shared) }

    private val mAlgorithmButton: Button by lazy { find<Button>(R.id.algorithm) }

    private val mLogButton: Button by lazy { find<Button>(R.id.log) }

    private val mPoolButton: Button by lazy { find<Button>(R.id.pool) }

    private val mAnimButton: Button by lazy { find<Button>(R.id.anim) }

    private val mViewButton: Button by lazy { find<Button>(R.id.view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mHandlerButton.setOnClickListener { intent(HandlerActivity::class) }
        mIntentButton.setOnClickListener { }
        mPermissionButton.setOnClickListener { }
        mServiceButton.setOnClickListener { }
        mFileButton.setOnClickListener { }
        mMemoryButton.setOnClickListener { intent(MemoryActivity::class) }
        mResourceButton.setOnClickListener { }
        mSharedButton.setOnClickListener { }
        mAlgorithmButton.setOnClickListener { }
        mLogButton.setOnClickListener { }
        mPoolButton.setOnClickListener { intent(PoolActivity::class) }
        mAnimButton.setOnClickListener { }
        mViewButton.setOnClickListener { }
    }
}
