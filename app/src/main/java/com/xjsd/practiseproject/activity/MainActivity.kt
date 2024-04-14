package com.xjsd.practiseproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjsd.practiseproject.BuildConfig
import com.xjsd.practiseproject.R
import com.xjsd.practiseproject.adapter.FuncAdapter
import com.xjsd.practiseproject.databinding.ActivityMainBinding
import com.xjsd.practiseproject.ext.logI

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mMainBinding.root)

        init()
    }


    private fun init() {
        initFuncList()
        // BuildConfig
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initFuncList() {
        val list = arrayListOf<String>().apply {
            add(getString(R.string.func_title_room))
            add(getString(R.string.func_title_coroutines))
        }
        mMainBinding.rvFunc.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ).apply {
                getDrawable(R.drawable.divider_rv_func_item)?.let { setDrawable(it) }
            }
        )
        mMainBinding.rvFunc.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        mMainBinding.rvFunc.adapter = FuncAdapter(list, onItemClick = { view, position ->
            onItemClick(position)
        })
    }

    private fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                startActivity(Intent(this, RoomActivity::class.java))
            }

            1 -> {
                startActivity(Intent(this, CoroutinesActivity::class.java))
            }

            else -> {
                "暂时无需处理的功能".logI(TAG)
            }
        }
    }
}