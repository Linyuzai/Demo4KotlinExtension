package com.linyuzai.demo4kotlinextension.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.linyuzai.demo4kotlinextension.R
import com.linyuzai.kotlinextension.find
import com.linyuzai.kotlinextension.res

class ResourceActivity : AppCompatActivity() {

    private val TAG: String = this::class.java.name

    private val mGetColorButton: Button by lazy { find<Button>(R.id.get_color) }

    private val mGetStringButton: Button by lazy { find<Button>(R.id.get_string) }

    private val mGetDrawableButton: Button by lazy { find<Button>(R.id.get_drawable) }

    private val mGetMipmapButton: Button by lazy { find<Button>(R.id.get_mipmap) }

    private val mResourceText: TextView by lazy { find<TextView>(R.id.resource_text) }

    private val mResourceImage: ImageView by lazy { find<ImageView>(R.id.resource_image) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)
        mGetColorButton.setOnClickListener {
            res().access().resId(R.color.colorAccent).color { mResourceText.setBackgroundColor(it) }.recycle()
        }
        mGetStringButton.setOnClickListener {
            res().access().resId(R.string.resource).string { mResourceText.text = it }.recycle()
        }
        mGetDrawableButton.setOnClickListener {
            res().access().resId(R.drawable.resource_drawable).drawable { mResourceImage.setImageDrawable(it) }.recycle()
        }
        mGetMipmapButton.setOnClickListener {
            res().access().resId(R.mipmap.ic_launcher_round).mipmap { mResourceImage.setImageDrawable(it) }.recycle()
        }
    }
}
