package com.noby.dptask.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.noby.dptask.R;


/**
 * Created by A.Noby on 8/29/2018.
 */
public class StaticMethods {

    public static final int REQUEST_LOCATION = 199;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public static boolean isConnectingToInternet(Context _context) {
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }

    public static void NOConnecting(CoordinatorLayout _context, Context context) {
        Snackbar snackbar = Snackbar
                .make(_context, context.getString(R.string.noconnection), Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        color = Color.RED;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }

    public static void ShowSnake(CoordinatorLayout _context, Context context, String message) {
        Snackbar snackbar = Snackbar
                .make(_context,message, Snackbar.LENGTH_LONG);
        int color;
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        color = Color.WHITE;
        textView.setTextColor(color);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        snackbar.show();
    }

    public  static boolean  checkLocationPermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                ActivityCompat.requestPermissions(activity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
}
