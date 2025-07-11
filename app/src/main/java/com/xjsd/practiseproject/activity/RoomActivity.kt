package com.xjsd.practiseproject.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xjsd.practiseproject.R
import com.xjsd.practiseproject.adapter.FuncAdapter
import com.xjsd.practiseproject.databinding.ActivityRoomBinding
import com.xjsd.practiseproject.ext.logI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext

class RoomActivity : BaseActivity(), CoroutineScope by MainScope() {
    companion object {
        private const val TAG = "RoomActivity"
    }

    private lateinit var mRoomBinding: ActivityRoomBinding
    private val mState by lazy { MutableSharedFlow<String>() }
    private var mState1 = MutableStateFlow("Hello")

    override fun getRootView(): View {
        return ActivityRoomBinding.inflate(layoutInflater).also {
            mRoomBinding = it
        }.root
    }

    override fun create(savedInstanceState: Bundle?) {
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
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {

            }
            supervisorScope {

            }
            coroutineScope {

            }
        }

        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                result.data
            }
        }
        launcher.launch(Intent(this, CoroutinesActivity::class.java))
    }
}