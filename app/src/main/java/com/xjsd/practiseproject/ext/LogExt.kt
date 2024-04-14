package com.xjsd.practiseproject.ext

import android.util.Log

private const val LOG_TAG = "PP-"

fun String.logI(tag: String) {
    Log.i("$LOG_TAG$tag", this)
}

fun String.logD(tag: String) {
    Log.d("$LOG_TAG$tag", this)
}

fun String.logE(tag: String, tr: Throwable) {
    Log.e("$LOG_TAG$tag", this, tr)
}