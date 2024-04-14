package com.xjsd.practiseproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xjsd.practiseproject.databinding.ActivityCoroutinesBinding

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var mCoroutinesBinding: ActivityCoroutinesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCoroutinesBinding = ActivityCoroutinesBinding.inflate(layoutInflater)
        setContentView(mCoroutinesBinding.root)

        init()
    }

    private fun init() {
        mCoroutinesBinding.titleBar.back {
            finish()
        }
    }
}