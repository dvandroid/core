package core.android.com.android_core

import android.os.Bundle
import core.android.com.corelib.dialog.CustomDialog
import core.android.com.corelib.permission.onRequestPermissionsResultReceived
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber.e
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        onRequestPermissionsResultReceived(requestCode, permissions, grantResults,
                onPermissionGranted = {
                    // show camera
                },
                onPermissionDenied = {
                    // show error dialog
                }
        )
    }
}
