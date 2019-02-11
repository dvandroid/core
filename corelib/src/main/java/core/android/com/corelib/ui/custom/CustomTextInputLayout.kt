package core.android.com.corelib.ui.custom

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet
import android.view.View.OnFocusChangeListener
import core.android.com.corelib.R

class CustomTextInputLayout : TextInputLayout {

    private var textHint: String? = null
    var label: String? = null

    constructor(context: Context?) : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0){
        initializeAttributes(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initializeAttributes(attrs)
    }

    init {
        textHint = hint.toString()
        if(hint.isNullOrBlank()) isHintEnabled = false
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        editText?.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            hint = if (hasFocus && !label.isNullOrBlank()) label else textHint
        }
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        val array = context.theme.obtainStyledAttributes(
                attrs, R.styleable.CustomTextInputLayout, 0, 0)
        try {
            label = array.getString(R.styleable.CustomTextInputLayout_ctil_label)

        } finally {
            array.recycle()
        }
    }
}