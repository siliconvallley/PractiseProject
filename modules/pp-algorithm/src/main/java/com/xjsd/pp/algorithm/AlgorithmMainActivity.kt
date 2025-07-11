package com.xjsd.pp.algorithm

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.xjsd.pp.algorithm.ui.theme.PractiseProjectTheme
import com.xjsd.pp.algorithm.view.TitleBar
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.xjsd.lib.algorithm.ThreeSums

private const val TAG = "AlgorithmMainActivity"

class AlgorithmMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PractiseProjectTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Log.d(TAG, "innerPadding=$innerPadding")
                    LayoutMain(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun LayoutMain(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top
    ) {
        // 1. TitleBar
        val context = LocalContext.current
        val title = context.getString(R.string.func_title_algorithm)

        TitleBar(
            title = title,
            iconBackResId = R.drawable.ic_title_back,
        ) {
            if (context is Activity) {
                context.finish()
            }
        }

        // 功能列表
        FuncList()
    }
}

@Composable
fun FuncList() {
    val funcList = arrayListOf<String>().apply {
        add(LocalContext.current.getString(R.string.item_algorithm_3sum))
    }

    LazyColumn {
        itemsIndexed(funcList) { index, item ->
            ListItem(
                headlineContent = {
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                },
                leadingContent = {
                    Icon(Icons.Default.Add, contentDescription = item)
                },
                modifier = Modifier.clickable {
                    handleFuncItemClick(index)
                }
            )
        }
    }
}

/**
 * 功能项点击事件
 *
 * @param index 功能项索引
 */
fun handleFuncItemClick(index: Int) {
    when (index) {
        0 -> {
            // 3Sum
            ThreeSums().threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)).run {
                forEach {
                    Log.d(TAG, "threeSum() result=$it")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PractiseProjectTheme {
        LayoutMain()
    }
}