package com.xjsd.practiseproject.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjsd.practiseproject.R
import com.xjsd.practiseproject.adapter.FuncAdapter
import com.xjsd.practiseproject.databinding.ActivityRoomBinding
import com.xjsd.practiseproject.ext.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    companion object {
        private const val TAG = "RoomActivity"
    }

    private lateinit var mRoomBinding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mRoomBinding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(mRoomBinding.root)

        init()
    }

    private fun init() {
        mRoomBinding.titleBar.back {
            finish()
        }

        initFuncList()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun initFuncList() {
        val list = arrayListOf<String>().apply {
            add("插入数据")
            add("删除数据")
            add("修改数据")
            add("查询单条数据")
            add("查询列表数据")
        }
        mRoomBinding.rvRoom.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            ).apply {
                getDrawable(R.drawable.divider_rv_func_item)?.let { setDrawable(it) }
            }
        )
        mRoomBinding.rvRoom.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        mRoomBinding.rvRoom.adapter = FuncAdapter(list, onItemClick = { view, position ->
            onItemClick(position)
        })
    }

    private fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                insertData()
            }

            1 -> {
                deleteData()
            }

            2 -> {
                updateData()
            }

            else -> {
                "暂时无需处理的功能".logI(TAG)
            }
        }
    }

    /**
     * 插入数据.
     */
    private fun insertData() {
        launch {
            withContext(Dispatchers.IO) {

            }
        }
    }

    /**
     * 删除数据.
     */
    private fun deleteData() {
        launch {
            withContext(Dispatchers.IO) {

            }
        }
    }

    /**
     * 更新数据.
     */
    private fun updateData() {

    }
}