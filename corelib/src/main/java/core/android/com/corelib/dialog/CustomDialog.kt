package core.android.com.corelib.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import core.android.com.corelib.R
import kotlinx.android.synthetic.main.custom_dialog.*
import javax.inject.Inject

open class CustomDialog @Inject constructor(context: Context) {

    var context : Context? = null
    var dialog : Dialog? = null

    var tvHeader :          TextView? = null
    var tvMessage:          TextView? = null
    var btnFirstButton:     Button? = null
    var btnSecondButton:    Button? = null
    var llButtons:          LinearLayout? = null

    init {

        this.context = context

        dialog = Dialog(context, R.style.CustomDialog)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.custom_dialog)
        dialog?.setCancelable(false)

        tvHeader        = dialog?.tv_header
        tvMessage       = dialog?.tv_message
        btnFirstButton  = dialog?.btn_first
        btnSecondButton = dialog?.btn_second
        llButtons       = dialog?.ll_buttons
    }

    fun showDialog(dialogBuilder: CustomDialogBuilder){

        ///////////////////////////////////////////////
        // Header
        ///////////////////////////////////////////////
        dialogBuilder.header?.apply {
            tvHeader?.visibility = View.VISIBLE
            tvHeader?.text = this
            setColor(tvHeader!!, dialogBuilder.headerColor)
        }

        ///////////////////////////////////////////////
        // Message
        ///////////////////////////////////////////////
        tvMessage?.text = dialogBuilder.message
        setColor(tvMessage!!, dialogBuilder.messageColor)

        ///////////////////////////////////////////////
        // First Button
        ///////////////////////////////////////////////
        btnFirstButton?.text = dialogBuilder.firstButton
        btnFirstButton?.background = ContextCompat.getDrawable(context!!, dialogBuilder.firstButtonBackground)
        setColor(btnFirstButton!!, dialogBuilder.firstButtonTextColor)

        btnFirstButton?.setOnClickListener {
            dialogBuilder.firstButtonAction?.invoke()
            dialog?.dismiss()
        }

        ///////////////////////////////////////////////
        // Second Button
        ///////////////////////////////////////////////
        dialogBuilder.secondButton?.apply {
            btnSecondButton?.visibility = View.VISIBLE
            btnSecondButton?.text = this
            btnSecondButton?.background = ContextCompat.getDrawable(context!!, dialogBuilder.secondButtonBackground)
            setColor(btnSecondButton!!, dialogBuilder.secondButtonTextColor)

            btnSecondButton?.setOnClickListener {
                dialogBuilder.secondButtonAction?.invoke()
                dialog?.dismiss()
            }

            //check orientation
            if(dialogBuilder.isVertical){
                llButtons?.orientation = LinearLayout.VERTICAL

                val params: LinearLayout.LayoutParams =
                            LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT)

                btnFirstButton?.layoutParams = params

                params.setMargins(0, 10, 0, 0)
                btnSecondButton?.layoutParams = params

            }
        }

        if(!dialog?.isShowing!!)
            dialog?.show()
    }

    fun isShowing(): Boolean?{
        return dialog?.isShowing
    }
}

fun CustomDialog.setColor(view : View, color : Int){
    if(view is TextView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            view.setTextColor(context!!.getColor(color))
         else
            view.setTextColor(ContextCompat.getColor(context!!, color))
    }
}