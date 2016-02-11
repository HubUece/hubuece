package com.luna9.swconnection;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class SWUtils {

    /** Checks whether or not network connectivity is available. */
    public static boolean checkNetworkConnection(Context context) {
        boolean status = false;

        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status = true;
            }
            else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status = true;
            }

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return status;
    }

    public  static void showAlertDialog(String title, String message,Context context) {

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.dlg_ok,null)
                .show();
    }

}
