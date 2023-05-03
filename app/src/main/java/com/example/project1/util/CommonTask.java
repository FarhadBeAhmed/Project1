package com.example.project1.util;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.project1.BuildConfig;
import com.example.project1.R;
import com.example.project1.service.CommonUrl;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import cz.msebera.android.httpclient.client.cache.Resource;

public class CommonTask {

    public static void savePreferences(Context context, String key, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void savePreferences(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getPreferences(Context context, String prefKey) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(prefKey, "");
    }

    public static boolean getPreferencesBoolean(Context context, String prefKey) {
        boolean z = false;
        try {
            z = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(prefKey, false);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return z;
        }
    }

    KProgressHUD progressDialog ;

    @SuppressLint("HardwareIds")
    public static String getDeviceID(Context context){
       return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public void controlProgressBar(Context context, boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.progressDialog != null && this.progressDialog.isShowing()) {
                    this.progressDialog.dismiss();
                }
                progressDialog.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {
            }
        } else if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
    }

    public static boolean IsReachable(Context context) {
        // First, check we have any sort of connectivity
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        boolean isReachable = false;

        if (netInfo != null && netInfo.isConnected()) {
            // Some sort of connection is open, check if server is reachable
            try {
                URL url = new URL(CommonUrl.BASE_URL);
                //URL url = new URL("http://10.0.2.2");
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestProperty("User-Agent", "Android Application");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5 * 1000);
                urlc.connect();
                isReachable = (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                //Log.e(TAG, e.getMessage());
            }
        }

        return isReachable;
    }
    public static boolean isHostReachable(String serverAddress, int serverTCPport, int timeoutMS){
        boolean connected = false;
        Socket socket;
        try {
            socket = new Socket();
            SocketAddress socketAddress = new InetSocketAddress(serverAddress, serverTCPport);
            socket.connect(socketAddress, timeoutMS);
            if (socket.isConnected()) {
                connected = true;
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket = null;
        }
        return connected;
    }

    public static void snackBarInternetConnection(Context context, View view){
        Snackbar snackbar;
        if (CheckInternetConnection.isInternetAvailable(context)){
            snackbar=Snackbar.make(view, "Connected", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(view.getResources().getColor(R.color.white));
            snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.green));
            snackbar.show();
        }else {
            snackbar=Snackbar.make(view, "Internet connection failed", Snackbar.LENGTH_INDEFINITE);
            snackbar.setActionTextColor(view.getResources().getColor(R.color.white));
            snackbar.getView().setBackgroundColor(Color.RED);
            snackbar.show();
        }

    }
    public static void snackBar(Context context, View view,String msg){
        Snackbar snackbar;

        snackbar=Snackbar.make(view, "Internet connection failed", Snackbar.LENGTH_SHORT);
        snackbar.setActionTextColor(view.getResources().getColor(R.color.white));
        snackbar.getView().setBackgroundColor(context.getResources().getColor(R.color.light_black));
        snackbar.show();


    }

    public static void showToastMessage(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(48, 0, 120);
        toast.show();
    }

    public static void showLog(String message) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, message == null ? "" : message);
        }
    }

    public static void showLog(String Tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(Tag, message == null ? "" : message);
        }
    }
    public static String getVersionName(Context context) {
        String versionName = "";
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return versionName;
        }
    }
}
