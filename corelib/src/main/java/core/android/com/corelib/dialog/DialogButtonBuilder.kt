package core.android.com.corelib.dialog

import android.graphics.drawable.Drawable
import core.android.com.corelib.R
import io.github.jffiorillo.builder.JvmBuilder

@JvmBuilder
data class DialogButtonBuilder(val label: String, val textColor : Int = R.color.white, val textSize: Int,
                               val background : Drawable? = null, var action : (() -> Unit)?)
