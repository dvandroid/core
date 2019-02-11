package core.android.com.android_core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import core.android.com.corelib.dialog.*
import core.android.com.corelib.helper.Validation
import core.android.com.corelib.ui.custom.BottomSheetListDialog
import dagger.Component
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var validation: Validation

    /*private val list = arrayListOf<String>("Item 1", "Item 2", "Item 3", "Item 4")
    private var bottomSheetListDialog: BottomSheetListDialog? = null*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       /* //create dialog builder
        var dialogBuilder = JvmBuilder_CustomDialogBuilder().header("HEADER")
                .headerColor(R.color.colorAccent)
                .message("Hello World")
                .firstButton("Ok")
                .firstButtonBackground(R.drawable.circular_button_red)
                .firstButtonTextColor(R.color.white)
                .firstButtonAction { Log.d("BUTTON", "FIRST") }
                .secondButton("Cancel")
                .secondButtonAction { Log.d("BUTTON", "SECOND") }
                .secondButtonTextColor(R.color.colorAccent)
                .secondButtonBackground(R.drawable.circular_button_red)
                .build()

        var customDialog = CustomDialog(this)
        customDialog.showDialog(dialogBuilder)*/

        /*bottom_sheet.sheetBehavior?.apply {
            isHideable = true
            peekHeight = 56.dpToPx
        }*/
        /*bottomSheetListDialog = BottomSheetListDialog(this, list)*/

    }
}

@Component
interface MainActivityComponent{
    fun inject(app : MainActivity)
}
