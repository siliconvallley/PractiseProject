package com.xjsd.pp.algorithm.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xjsd.pp.algorithm.R

import androidx.compose.ui.platform.LocalContext

@Composable
fun TitleBar(
    modifier: Modifier = Modifier,
    title: String = LocalContext.current.resources.getString(R.string.back),
    @DrawableRes iconBackResId: Int = R.drawable.ic_title_back,
    onBack: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clickable(onClick = onBack)
        ) {
            Image(
                painter = painterResource(id = iconBackResId),
                contentDescription = title,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
                    .align(Alignment.Center)
            )
        }

        // Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TitleBarPreview() {
    TitleBar()
}