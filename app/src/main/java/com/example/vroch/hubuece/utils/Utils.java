package com.example.vroch.hubuece.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.vroch.hubuece.R;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {

    /** Checks whether or not network connectivity is available. Displays an error dialog if there
    is no connectivity. */
    public static boolean checkNetworkConnection(Context context, boolean showDialog) {
        boolean status = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);

            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }
            else {
                netInfo = cm.getNetworkInfo(1);
                if (netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }

        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        //Shows dialog if there is no network connection.
        if (!status && showDialog) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.dlg_network_notavailable_title);
            builder.setMessage(R.string.dlg_network_notavailable_message);
            builder.setPositiveButton(R.string.dlg_positive_ok, null);
            builder.show();
        }

        return status;
    }

    /** Generates an MD5 hash string from the passed string. */
    public static String generateMD5(String string) {
        String hashedString = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(string.getBytes("UTF-8"));

            //This bytes[] has bytes in decimal format.
            byte[] bytesResult = digest.digest();

            //Convert it to hexadecimal format.
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < bytesResult.length; i++) {
                builder.append(Integer.toString((bytesResult[i] & 0xff) + 0x100, 16).substring(1));
            }

            //Get complete hashed password in hex format
            hashedString = builder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return hashedString;
    }

    public static void dismissKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static SimpleDateFormat getUTCDateFormat() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        fmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return fmt;
    }

    public static String convertDateToLocalFormat(String utcDate) {
        //Parse the date that comes from the sever.
        SimpleDateFormat fmtDate = Utils.getUTCDateFormat();

        String formattedDate = "";
        try {
            //Convert the server date to the format of the current locale.
            DateFormat localDateFmt = SimpleDateFormat.getDateInstance();
            formattedDate = localDateFmt.format(fmtDate.parse(utcDate));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }


    public static void showDialog(String title, String message, Activity activity,
                                  DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.dlg_positive_ok, l);

        builder.show();
    }

    public static void showDialog(int title, int message, Activity activity,
                                  DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        if (title != 0)
            builder.setTitle(title);

        builder.setMessage(message);
        builder.setPositiveButton(R.string.dlg_positive_ok, l);

        builder.show();
    }

    public static void showNetworkErrorDialog(Activity activity, DialogInterface.OnClickListener l) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setTitle(R.string.dlg_network_error_title);
        builder.setMessage(R.string.dlg_network_error_message);
        builder.setPositiveButton(R.string.dlg_positive_ok, l);

        builder.show();
    }
}
