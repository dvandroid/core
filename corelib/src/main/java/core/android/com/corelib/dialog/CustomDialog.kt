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

class CustomDialog @Inject constructor(context: Context) {

    var context : Context? = null
    var dialog : Dialog? = null

    var tvHeader :          TextView? = null
    var tvMessage:          TextView? = null
    var btnFirstButton:     Button? = null
    var btnSecondButton:    Button? = null
    var llButtons:          LinearLayout? = null

    init {

        this.context = context

        dialog = Dialog(context)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.custom_dialog)
        dialog?.setCancelable(false)

        tvHeader        = dialog?.tv_header
        tvMessage       = dialog?.tv_message
        btnFirstButton  = dialog?.btn_first
        btnSecondButton = dialog?.btn_second
        llButtons       = dialog?.ll_buttons
    }


    fun showDialog(dialogBuilder: DialogBuilder){


        ///////////////////////////////////////////////
        // Header
        ///////////////////////////////////////////////
        dialogBuilder.headerBuilder?.apply {

            tvHeader?.visibility = View.VISIBLE
            tvHeader?.text = this.title
            tvHeader?.textSize = this.size.toFloat()

            setColor(tvHeader!!.rootView, this.color)

        }

        ///////////////////////////////////////////////
        // Message
        ///////////////////////////////////////////////
        tvMessage?.text = dialogBuilder.messageBuilder.message
        tvMessage?.textSize = dialogBuilder.messageBuilder.size.toFloat()

        setColor(tvMessage!!.rootView, dialogBuilder.messageBuilder.color)

        ///////////////////////////////////////////////
        // First Button
        ///////////////////////////////////////////////
        btnFirstButton?.text = dialogBuilder.firstButtonBuilder.label
        btnFirstButton?.background = dialogBuilder.firstButtonBuilder.background
        btnFirstButton?.textSize = dialogBuilder.firstButtonBuilder.textSize.toFloat()

        btnFirstButton?.setOnClickListener {
            dialogBuilder.firstButtonBuilder.action?.invoke()
        }

        setColor(btnFirstButton!!.rootView, dialogBuilder.firstButtonBuilder.textColor)

        ///////////////////////////////////////////////
        // Second Button
        ///////////////////////////////////////////////
        dialogBuilder.secondButtonBuilder?.apply {
            btnSecondButton?.visibility = View.VISIBLE
            btnSecondButton?.text = this.label
            btnSecondButton?.background = this.background
            btnSecondButton?.textSize = this.textSize.toFloat()

            btnSecondButton?.setOnClickListener {
                this.action?.invoke()
            }

            setColor(btnSecondButton!!.rootView, this.textColor)
        }

        if(dialog?.isShowing!!)
            dialog?.show()
    }

    private fun setColor(view : View, color : Int){
        if(view is Button && view is TextView){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                view?.setTextColor(context!!.getColor(color))
            else
                view?.setTextColor(ContextCompat.getColor(context!!, color))
        }
    }

    fun isShowing(): Boolean?{
        return dialog?.isShowing
    }
}