package core.android.com.corelib.dialog

import core.android.com.corelib.R
import io.github.jffiorillo.builder.JvmBuilder

@JvmBuilder
data class DialogMessageBuilder(val message: String, val size: Int = R.dimen.text, val color: Int = R.color.text)