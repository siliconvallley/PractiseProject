package com.xjsd.practiseproject.activity

import android.content.Context
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.xjsd.practiseproject.databinding.ActivityCoroutinesBinding
import com.xjsd.practiseproject.ext.logI

class CoroutinesActivity : BaseActivity() {
    companion object {
        private const val TAG = "CoroutinesActivity"
    }

    private lateinit var mCoroutinesBinding: ActivityCoroutinesBinding

    override fun getRootView(): View {
        return ActivityCoroutinesBinding.inflate(layoutInflater).also {
            mCoroutinesBinding = it
        }.root
    }

    override fun create(savedInstanceState: Bundle?) {
        initListener()
    }

    private fun initListener() {
        mCoroutinesBinding.titleBar.setOnClickListener {
            finish()
        }
        mCoroutinesBinding.btToSpeaker.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                switchToSpeaker()
            } else {
                "switchToSpeaker is version too low".logI(TAG)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun switchToSpeaker() {
        "switchToSpeaker".logI(TAG)
        val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val devices = am.availableCommunicationDevices
        var speakerDevice: AudioDeviceInfo? = null
        for (device in devices) {
            "switchToSpeaker ${device.type}, ${device.productName}".logI(TAG)
            if (device.type == AudioDeviceInfo.TYPE_BUILTIN_SPEAKER) {
                speakerDevice = device
                break
            }
        }
        speakerDevice?.let {
            val result = am.setCommunicationDevice(it)
            "switchToSpeaker is result=$result".logI(TAG)
        } ?: run {
            "switchToSpeaker speaker is null".logI(TAG)
        }
    }
}