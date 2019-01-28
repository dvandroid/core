package core.android.com.android_core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import core.android.com.corelib.dialog.*
import core.android.com.corelib.helper.Validation
import dagger.Component
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var validation: Validation

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

    }
}

@Component
interface MainActivityComponent{
    fun inject(app : MainActivity)
}
