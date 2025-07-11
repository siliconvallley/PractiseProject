package com.xjsd.practiseproject.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val rootView = getRootView()
        setContentView(rootView)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        create(savedInstanceState)
    }

    /**
     * 返回xml布局的View，用于setContentView.
     *
     * @return ContentView
     */
    abstract fun getRootView(): View

    /**
     * 初始化操作.
     * * 在getContentView()之后调用
     *
     * @param savedInstanceState savedInstanceState
     */
    abstract fun create(savedInstanceState: Bundle?)
}