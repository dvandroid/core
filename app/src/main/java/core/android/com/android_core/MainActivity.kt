package core.android.com.android_core

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import core.android.com.corelib.permission.Permission
import core.android.com.corelib.permission.PermissionCallback

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Permission.checkPermission(this, Manifest.permission.READ_CONTACTS) {
            when (it) {
                PermissionCallback.onNeedPermission -> {

                }

                PermissionCallback.onPermissionGranted -> {

                }

                PermissionCallback.onPermissionDisabled -> {

                }

                PermissionCallback.onPermissionPreviouslyDenied -> {

                }
            }
        }
    }
}
