package core.android.com.android_core.modules.login


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import core.android.com.android_core.Login
import core.android.com.android_core.R
import core.android.com.android_core.api.endpoints.FirstTestApi
import core.android.com.corelib.permission.AppPermission
import core.android.com.corelib.permission.handlePermission
import core.android.com.corelib.permission.onRequestPermissionsResultReceived
import core.android.com.corelib.permission.requestPermission
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject


class LoginFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        btn_login.setOnClickListener { doLogin() }

    }

    private fun doLogin() {
        showCameraPreviewScreen()

    }

    fun showCameraPreviewScreen() {
        handlePermission(AppPermission.CAMERA,
                onGranted = {
                    Toast.makeText(activity!!, "Granted", Toast.LENGTH_LONG).show()
                }, onDenied = {
            requestPermission(it)
        }, onRationaleNeeded = {
            snackbarWithAction(it.explanationMessageId) { requestPermission(it) }
        })
    }

    fun snackbarWithAction(messageId: Int, actionText: Int = R.string.request_permission, action: () -> Unit) {
        Snackbar.make(cl_parent, messageId, Snackbar.LENGTH_LONG)
                .setAction(actionText) { action() }
                .show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResultReceived(requestCode, permissions, grantResults,
                onPermissionGranted = {
                   // go to specific request permission
                },
                onPermissionDenied = {
                    snackbarWithAction(it.deniedMessageId, R.id.action_text) {
                        openPermissionSettingsScreen()
                    }
                }
        )
    }

    private fun openPermissionSettingsScreen() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        intent.data = Uri.fromParts("package", activity?.packageName, null)
        startActivity(intent)
    }
}

