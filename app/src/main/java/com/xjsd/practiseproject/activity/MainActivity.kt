package com.xjsd.practiseproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjsd.pp.algorithm.AlgorithmMainActivity
import com.xjsd.practiseproject.R
import com.xjsd.practiseproject.adapter.FuncAdapter
import com.xjsd.practiseproject.databinding.ActivityMainBinding
import com.xjsd.practiseproject.ext.logI

class MainActivity : BaseActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var mMainBinding: ActivityMainBinding

    override fun getRootView(): View {
        return ActivityMainBinding.inflate(layoutInflater).also {
            mMainBinding = it
        }.root
    }

    override fun create(savedInstanceState: Bundle?) {
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
            add(getString(R.string.func_title_algorithm))
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

            2 -> {
                startActivity(Intent(this, AlgorithmMainActivity::class.java))
            }

            else -> {
                "暂时无需处理的功能".logI(TAG)
            }
        }
    }
}