package core.android.com.corelib.dialog

import core.android.com.corelib.R
import io.github.jffiorillo.builder.JvmBuilder

@JvmBuilder
data class CustomDialogBuilder(val header : String? = null, val headerColor : Int = R.color.black,
                               val message : String, val messageColor : Int = R.color.text,
                               val firstButton: String, val firstButtonTextColor : Int = R.color.black,
                               val firstButtonBackground : Int = R.drawable.default_button, var firstButtonAction : (() -> Unit)?,
                               val secondButton: String? = null, val secondButtonTextColor : Int = R.color.black,
                               val secondButtonBackground : Int = R.drawable.default_button, var secondButtonAction : (() -> Unit)?,
                               val isVertical: Boolean = false)