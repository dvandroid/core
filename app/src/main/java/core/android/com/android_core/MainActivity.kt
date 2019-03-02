package core.android.com.android_core

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import core.android.com.android_core.view.ButtonFragmentSample
import core.android.com.android_core.view.CameraSample
import core.android.com.android_core.view.QRScannerSample
import core.android.com.corelib.camera.CameraUtils
import core.android.com.corelib.camera.IMAGE_URI
import core.android.com.corelib.camera.REQUEST_CAMERA_INTENT
import core.android.com.corelib.helper.Validation
import core.android.com.corelib.helper.addFragment
import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), View.OnClickListener {


    @Inject
    lateinit var validation: Validation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_camera.setOnClickListener(this)
        btn_qr.setOnClickListener(this)
        btn_btns.setOnClickListener(this)
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


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_camera ->
                startActivityForResult(Intent(this, CameraSample::class.java), REQUEST_CAMERA_INTENT)
            R.id.btn_qr ->
                startActivityForResult(Intent(this, QRScannerSample::class.java), REQUEST_CAMERA_INTENT)
            R.id.btn_btns ->
                addFragment(ButtonFragmentSample(), R.id.contentFrame)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CAMERA_INTENT -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val options = BitmapFactory.Options()
                        options.inPreferredConfig = Bitmap.Config.ARGB_8888
                        val bitmap = CameraUtils.getRotatedBitmap(data!!.getStringExtra(IMAGE_URI))
                    }
                }
            }
        }
    }
}


@Component
interface MainActivityComponent {
    fun inject(app: MainActivity)
}
