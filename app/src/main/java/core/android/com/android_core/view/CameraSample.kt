package core.android.com.android_core.view

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import core.android.com.android_core.R
import core.android.com.corelib.camera.CameraUtils
import core.android.com.corelib.camera.CameraView
import core.android.com.corelib.camera.IMAGE_URI
import core.android.com.corelib.camera.REQUEST_CAMERA_PERMISSION
import kotlinx.android.synthetic.main.camera_sample.*

class CameraSample : AppCompatActivity() {
    private var mBackgroundHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Developer can add frame and other stuffs in camera_sample.xml to overlay the [CameraView]
         */
        setContentView(R.layout.camera_sample)
        camera.addCallback(mCallback)
        btn_capture.setOnClickListener(mOnClickListener)
    }

    private val mOnClickListener = View.OnClickListener { v ->
        when (v.id) {
            R.id.btn_capture -> camera!!.takePicture()
        }
    }

    private val mCallback = object : CameraView.Callback() {
        override fun onSaveError() {
            //TODO Display error message
        }

        override fun onImageSaved(filename: String) {

            val intent = intent
            intent.putExtra(IMAGE_URI, filename)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }

        override fun onCameraOpened(cameraView: CameraView) {}

        override fun onCameraClosed(cameraView: CameraView) {}

        override fun onPictureTaken(cameraView: CameraView, data: ByteArray) {
            if (mBackgroundHandler == null)
                mBackgroundHandler = CameraUtils.startBackgroundHandler()
            mBackgroundHandler!!.post {
                camera.saveImage("image_filename", data)
            }
        }
    }


    override fun onStop() {
        super.onStop()
        camera.stop()
    }

    override fun onResume() {
        super.onResume()
      /*  if (Permission.shouldAskPermission(this, Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION)
        } else
            camera.start()*/
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (permissions.size != 1 || grantResults.size != 1) {
                    throw RuntimeException("Error on requesting camera permission.")
                }
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    //TODO  show dialog
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mBackgroundHandler != null) {

            mBackgroundHandler!!.looper.quit()

            mBackgroundHandler = null
        }
    }

}