package core.android.com.corelib.dialog

import io.github.jffiorillo.builder.JvmBuilder

@JvmBuilder
class DialogBuilder(val headerBuilder : DialogHeaderBuilder? = null,
                    val messageBuilder: DialogMessageBuilder,
                    val firstButtonBuilder: DialogButtonBuilder,
                    val secondButtonBuilder: DialogButtonBuilder? = null,
                    val isHorizontal : Boolean = false)
