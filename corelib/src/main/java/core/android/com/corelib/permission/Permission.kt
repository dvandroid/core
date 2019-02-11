package core.android.com.corelib.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import core.android.com.corelib.permission.PermissionCallback.onPermissionGranted
import core.android.com.corelib.permission.PermissionCallback.onPermissionDisabled
import core.android.com.corelib.permission.PermissionCallback.onNeedPermission
import core.android.com.corelib.permission.PermissionCallback.onPermissionPreviouslyDenied
import android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale


object Permission {


    /** Check if version is marshmallow and above.
     *  Used in deciding to ask runtime permission
     */
    private fun shouldAskPermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }


    private fun shouldAskPermission(context: Context?, permission: String?): Boolean {
        if (shouldAskPermission()) {
            if (ActivityCompat.checkSelfPermission(context!!, permission!!) != PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return true
    }

    /** Request permission for a single time
     *
     * @param context = context of activity
     * @param permissions = list of permission this should be the format
     *
     *  Ex: arrayOf<String>(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS,
     *  Manifest.permission.WRITE_EXTERNAL_STORAGE)
     */

    fun shouldAskPermission(context: Context?, vararg permissions: String?): Boolean {
        if (shouldAskPermission()) {
            permissions.forEach { permission ->
                if (ActivityCompat.checkSelfPermission(context!!, permission!!) != PackageManager.PERMISSION_GRANTED) {
                    return true
                }
            }
        }
        return false
    }


    fun checkPermission(context: Context, permission: String, callback: (PermissionCallback) -> Unit) {

        if (shouldAskPermission(context, permission)) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                callback.invoke(PermissionCallback.onNeedPermission)
            } else {

                /** Permission denied or first time requested
                 **/
                /* if (PreferencesUtcontextil.isFirstTimeAskingPermission(context, permission)) {
                  //   PreferencesUtil.firstTimeAskingPermission(context, permission, false)
                  //  callback.invoke(PermissionCallback.onNeedPermission)
                 } else {

                // callback.invoke(PermissionCallback.onPermissionDisabled)

                }
             */
            }

            /** Handle the feature without permission or ask user to manually allow permission
             */
            callback.invoke(PermissionCallback.onPermissionDisabled)
        } else {
            callback.invoke(PermissionCallback.onPermissionGranted)
        }
    }

}