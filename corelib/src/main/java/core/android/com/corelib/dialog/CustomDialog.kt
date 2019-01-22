package core.android.com.corelib.dialog

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.Window
import android.widget.Button
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

    init {

        this.context = context

        dialog = Dialog(context)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setContentView(R.layout.custom_dialog)
        dialog?.setCancelable(false)

        tvHeader        = dialog?.findViewById(R.id.tv_header)
        tvMessage       = dialog?.findViewById(R.id.tv_message)
        btnFirstButton  = dialog?.findViewById(R.id.btn_first)
        btnSecondButton = dialog?.findViewById(R.id.btn_second)

       // dialog?.tv_header
    }


    fun showDialog(dialogBuilder: DialogBuilder){

        ///////////////////////////////////////////////
        // Header
        ///////////////////////////////////////////////
        dialogBuilder.headerBuilder?.apply {

            tvHeader?.visibility = View.VISIBLE
            tvHeader?.text = this.title
            tvHeader?.textSize = this.size.toFloat()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                tvHeader?.setTextColor(context!!.getColor(this.color))
             else
                tvHeader?.setTextColor(ContextCompat.getColor(context!!, this.color))

        }

        ///////////////////////////////////////////////
        // Message
        ///////////////////////////////////////////////
        tvMessage?.text = dialogBuilder.messageBuilder.message
        tvMessage?.textSize = dialogBuilder.messageBuilder.size.toFloat()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            tvMessage?.setTextColor(context!!.getColor(dialogBuilder.messageBuilder.color))
        else
            tvMessage?.setTextColor(ContextCompat.getColor(context!!, dialogBuilder.messageBuilder.color))

        ///////////////////////////////////////////////
        // First Button
        ///////////////////////////////////////////////
        btnFirstButton?.text = dialogBuilder.firstButtonBuilder.label
        btnFirstButton?.background = dialogBuilder.firstButtonBuilder.background
        btnFirstButton?.textSize = dialogBuilder.firstButtonBuilder.textSize.toFloat()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            btnFirstButton?.setTextColor(context!!.getColor(dialogBuilder.firstButtonBuilder.textColor))
        else
            btnFirstButton?.setTextColor(ContextCompat.getColor(context!!, dialogBuilder.firstButtonBuilder.textColor))

        btnFirstButton?.setOnClickListener {
            dialogBuilder.firstButtonBuilder.action?.invoke()
        }

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


        }

        ///////////////////////////////////////////////
        // orientation
        ///////////////////////////////////////////////
        if(dialogBuilder.isHorizontal){
            dialogBuilder.secondButtonBuilder?.apply {
                //change position of first & second button
            }
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