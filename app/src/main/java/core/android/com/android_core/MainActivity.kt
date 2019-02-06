package core.android.com.android_core

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.widget.Button
import core.android.com.android_core.api.endpoints.FirstTestApi
import core.android.com.corelib.helper.Validation
import core.android.com.corelib.permission.AppPermission
import core.android.com.corelib.permission.handlePermission
import core.android.com.corelib.permission.onRequestPermissionsResultReceived
import core.android.com.corelib.permission.requestPermission
import dagger.Component
import dagger.android.support.DaggerAppCompatActivity
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
