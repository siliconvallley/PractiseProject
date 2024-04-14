package com.xjsd.practiseproject.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.xjsd.practiseproject.R
import com.xjsd.practiseproject.databinding.LayoutTitleBarBinding

class TitleBar : ConstraintLayout {
    private var mBinding: LayoutTitleBarBinding

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mBinding = LayoutTitleBarBinding.inflate(LayoutInflater.from(context), this, true)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val backResId =
            typedArray.getResourceId(R.styleable.TitleBar_title_bar_icon, R.drawable.ic_title_back)
        val title = typedArray.getString(R.styleable.TitleBar_title_bar_title)
            ?: resources.getString(R.string.title_bar_back)
        typedArray.recycle()

        mBinding.ivBack.setImageResource(backResId)
        mBinding.tvTitle.text = title
    }

    /**
     * 返回.
     *
     * @param onBack 返回按钮回调
     */
    fun back(onBack: () -> Unit) {
        mBinding.ivBack.setOnClickListener {
            onBack.invoke()
        }
    }
}