package bhapps.utilitools.permissions.kotlin

import android.app.Activity
import android.app.Instrumentation
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.ArrayList

class Permissions {

    fun getRequiredPermissions(context: Context): Array<String?> {

        try {

            var info = context.packageManager.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS)
            val requestedPermissions = info.requestedPermissions
            if (requestedPermissions != null && requestedPermissions.isNotEmpty()) {
                return requestedPermissions
            }else{
                return kotlin.arrayOfNulls(0)
            }

        } catch (exception: Exception) {
            return arrayOfNulls(0)
        }
    }

    fun checkAllPermissionsGrantedInArray(context: Context, requiredPermissions: Array<String?>): Boolean {
        for (permission in getRequiredPermissions(context)) {
            if (!isPermissionGranted(context, permission)) {
                return false
            }
        }
        return true
    }

    fun isPermissionGranted(context: Context, permission: String?): Boolean {
        if (ContextCompat.checkSelfPermission(context, permission!!) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    internal fun Activity.validatePermission(
        permissionToValidate: String,
        recursiveCall: (() -> Boolean) = { false }
    ): Boolean {
        val permission = ContextCompat.checkSelfPermission(
            this,
            permissionToValidate
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (recursiveCall()) {
                return false
            }

            ActivityCompat.requestPermissions(
                this,
                arrayOf(permissionToValidate),
                110
            )
            return this.validatePermission(permissionToValidate) { true }
        }

        return true

    }

}