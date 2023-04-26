package com.example.project1.util;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.example.project1.BuildConfig;
import com.example.project1.R;
import com.kaopiz.kprogresshud.KProgressHUD;

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
