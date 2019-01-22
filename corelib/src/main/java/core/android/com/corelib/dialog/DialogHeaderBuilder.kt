package core.android.com.corelib.dialog

import core.android.com.corelib.R
import io.github.jffiorillo.builder.JvmBuilder

@JvmBuilder
data class DialogHeaderBuilder(val title: String, val size: Int = R.dimen.header, val color: Int = R.color.black)