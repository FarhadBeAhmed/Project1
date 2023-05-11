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

import androidx.annotation.NonNull;

import com.example.project1.BuildConfig;
import com.example.project1.R;
import com.example.project1.service.CommonUrl;
import com.example.project1.view.ui.DomainConnectActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;

import cn.pedant.SweetAlert.SweetAlertDialog;
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

   /*  new SweetAlertDialog(DomainConnectActivity .this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Wrong")
                        .setContentText(response.getMsg())
            .setConfirmText("OK")
                        .show();
*/
    KProgressHUD progressDialog;

    @SuppressLint("HardwareIds")
    public static String getDeviceID(Context context) {
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

    public static boolean isHostReachable(String serverAddress, int serverTCPport, int timeoutMS) {
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

    public static void snackBarInternetConnection(Context context, View view) {
        Snackbar snackbar;
        if (CheckInternetConnection.isInternetAvailable(context)) {
            snackbar = Snackbar.make(view, "Connected", Snackbar.LENGTH_SHORT);
            snackbar.setActionTextColor(view.getResources().getColor(R.color.white));
            snackbar.getView().setBackgroundColor(view.getResources().getColor(R.color.green));
            snackbar.show();
        } else {
            snackbar = Snackbar.make(view, "Internet connection failed", Snackbar.LENGTH_INDEFINITE);
            snackbar.setActionTextColor(view.getResources().getColor(R.color.white));
            snackbar.getView().setBackgroundColor(Color.RED);
            snackbar.show();
        }
    }

    public static void snackBar(Context context, View view, String msg) {
        Snackbar snackbar;
        snackbar = Snackbar.make(view, "Internet connection failed", Snackbar.LENGTH_SHORT);
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

    @NonNull
    public static String getTextFromTIL(@NonNull TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }


    public static void addMultipleClickListener(View.OnClickListener v, @NonNull View... views) {
        for (View b : views) b.setOnClickListener(v);
    }

    /**
     * If you write blank string, It can return only <b>website</b>.<br>
     * We've create a only website value, So use that.
     *
     * @param strings Use ("api", "*.php") instead of ("api/*.php") in this inner method.
     * @return Get File Name As Host Name Shortcut.
     */

    @NotNull
    public static String getFileNameAsHost(@NonNull String @NotNull ... strings) {
        String result = CommonUrl.BASE_URL;
        for (String f : strings) {
            result += f + "/";
        }
        result += "/";
        result = result.replace("//", "");
        return result;
    }

    @NotNull
    public static String getImageUrl(@NonNull String @NotNull ... strings) {
        String result = CommonUrl.BASE_URL;
        for (String f : strings) {
            result += f + "/";
        }
        result += "/";
        result = result.replace("//", "");
        return result;
    }

    /**
     * What all of {@link TextInputLayout} checked is text completed or not.
     *
     * @param textInputLayouts enter the all {@link TextInputLayout}
     * @return is {@link TextInputLayout}(s) text completed or not.
     */
    public static boolean validation(TextInputLayout @NotNull ... textInputLayouts) {
        boolean result = true;
        for (TextInputLayout til : textInputLayouts) {
            if (!(til.getEditText().getText().toString().length() < 0)) {
                til.setError("");
            }
            if (til.getEditText().getText().toString().isEmpty()) {
                til.setError("Field can't be empty.");
                result = false;
            }
        }
        return result;
    }

    public static boolean passwordValidation(TextInputLayout @NotNull ... textInputLayouts) {
        boolean result = true;
        for (TextInputLayout til : textInputLayouts) {
            if (!(til.getEditText().getText().toString().length() < 6)) {
                til.setError("");
            }
            if (til.getEditText().getText().toString().length() < 6) {
                til.setError("Minimum 6 character");
                result = false;
            }
        }
        return result;
    }

    @NonNull
    public static String getTextFromTextInputLayout(@NonNull TextInputLayout textInputLayout) {
        return textInputLayout.getEditText().getText().toString();
    }

    @NonNull
    public static String getRealStringEscape(String s) {
        String result = s;
        if (result == null) result = "";
        result = result.replace("`", "");
        result = result.replace("'", "");
        result = result.replace("\"", "");
        result = result.replace("null", "");
        return result;
    }

    /**
     * You get the url with submit value.
     *
     * @param directory enter your directory on getFileNameAsHost()
     * @param variable  enter your variable name on _String ... [0] <br>
     *                  Then enter your variable on _String ... [1]
     * @return you get the url with submit value.
     */
    @NonNull
    public static String GetMethodURL(String directory, @NonNull String[]... variable) {
        String result = directory + "?&";
        for (String[] var : variable)
            result += "&" + var[0] + "=" + var[1];
        result = result.replace("&&", "");
        return result;
    }

}
