package core.android.com.corelib.permission

sealed class PermissionCallback {

    object onNeedPermission : PermissionCallback()
    object onPermissionPreviouslyDenied : PermissionCallback()
    object onPermissionDisabled : PermissionCallback()
    object onPermissionGranted : PermissionCallback()

}
