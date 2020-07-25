package bhapps.utilitools.permissions.java;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {

    /*
     *
     * checkPermissions(activity, int[])
     * check permissions in passed Array List, refer to variable bhapps.utilitools.kotlin.core.ApplicationConfig.bhapps_utilitools_kotlin_core_variables_permissions_list
     * bhapps.utilitools.java.security.permissions.PermissionsCheck.checkPermissions(activity, int[])
     *
     * hot to use:
     *
     * //set permissions list to check, add your permissions, refer to refer to variable bhapps.utilitools.kotlin.core.ApplicationConfig.bhapps_utilitools_kotlin_core_variables_permissions_list
     * val permissions_list_to_check = intArrayOf(
     *      Manifest.permission.ACCESS_FINE_LOCATION,
     *      Manifest.permission.ACCESS_COARSE_LOCATION
     *
     * )
     *
     * //method in activity of fragment
     * if (bhapps.utilitools.java.security.permissions.PermissionsCheck.checkPermissions(activity, permissions_list_to_check)) {
                Toast.makeText(
                    context,
                    "Permissions have Granted",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "Permissions were Denied",
                    Toast.LENGTH_SHORT
                ).show()
        }
     *
    */

    public boolean checkPermissionsWithBooleanResult(Activity activity, String[] permissions_list) {

        List<String> checkedPermissionsList = new ArrayList<>();
        for (String index : permissions_list) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    index
            )
                    != PackageManager.PERMISSION_GRANTED) {
                    checkedPermissionsList.add(index);
            }
        }

        if(!checkedPermissionsList.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkPermissionsWithDialogResult(Activity activity, String[] permissions_list) {

        List<String> checkedPermissionsList = new ArrayList<>();
        for (String index : permissions_list) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    index
            )
                != PackageManager.PERMISSION_GRANTED) {
                    checkedPermissionsList.add(index);
                }
        }

        if(!checkedPermissionsList.isEmpty()) {
            ActivityCompat.requestPermissions(
                    activity,
                    checkedPermissionsList.toArray(new String[checkedPermissionsList.size()]),
                    101);
            return false;
        }
        return true;

    }

    //add more methods
    //like ...
    //show dialog with checkbox list for each ungranted permission

    /*
    showDialogWithPermissionsUpdateCheckboxForm(Activity activity, int[] bhapps_utilitools_kotlin_core_variables_permissions_list){


    }
    */

}