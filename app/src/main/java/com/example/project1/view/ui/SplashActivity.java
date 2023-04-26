package com.example.project1.view.ui;

import static android.telephony.TelephonyManager.NETWORK_TYPE_1xRTT;
import static android.telephony.TelephonyManager.NETWORK_TYPE_CDMA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EDGE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_0;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_A;
import static android.telephony.TelephonyManager.NETWORK_TYPE_EVDO_B;
import static android.telephony.TelephonyManager.NETWORK_TYPE_GPRS;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSDPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPA;
import static android.telephony.TelephonyManager.NETWORK_TYPE_HSPAP;
import static android.telephony.TelephonyManager.NETWORK_TYPE_IDEN;
import static android.telephony.TelephonyManager.NETWORK_TYPE_LTE;
import static android.telephony.TelephonyManager.NETWORK_TYPE_NR;
import static android.telephony.TelephonyManager.NETWORK_TYPE_UMTS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.project1.service.FHelper.User;
import com.example.project1.R;
import com.google.android.material.snackbar.Snackbar;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    View thisView;
    String type = "", dataType = "";
    ProgressBar progressBar;
    FrameLayout.LayoutParams params;
    Snackbar snackbar = null;
    static User user=new User();
    //static String uUrl= User.getSession().isDomainSaved();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        thisView = findViewById(R.id.splashId);
        progressBar = findViewById(R.id.progressBar);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user.getSession().isLoggedIn()) {
                    startActivity(new Intent(getApplicationContext(), DomainConnectActivity.class));
                    finish();
                }
                checkConnect();
                //startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
            }
        },2000);



    }

    public void checkInEveryTwosec() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                checkConnect();
            }
        }, 2000);
    }

    public void checkConnect() {

        if (haveNetworkConnection()) {
            if (type.equalsIgnoreCase("MOBILE")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    checkDatatype();
                }


               // startActivity(new Intent(this, DomainConnectActivity.class));
                if (dataType.equalsIgnoreCase("2G")) {
                    snackbar = Snackbar.make(thisView, type + " _ Internet Is Slow _" + dataType, Snackbar.LENGTH_INDEFINITE);
                } else {
                    snackbar = Snackbar.make(thisView, type + "__" + dataType, Snackbar.LENGTH_INDEFINITE);
                }

            } else if (type.equalsIgnoreCase("WIFI")) {
               // startActivity(new Intent(this, LoginActivity.class));
                startActivity(new Intent(this, DomainConnectActivity.class));
                snackbar = Snackbar.make(thisView, type + "__" + dataType, Snackbar.LENGTH_INDEFINITE);
            }
        } else {

            snackbar = Snackbar.make(thisView, "Internet Is not Connect. Please turn on your internet/WI-FI.", Snackbar.LENGTH_INDEFINITE);
            checkInEveryTwosec();
        }

        View view = snackbar.getView();
        params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.TOP;
        params.setMargins(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setForegroundGravity(Gravity.CENTER);
        }
        view.setBackgroundColor(0xFF111111);
        view.setPadding(0, 0, 0, 0);
        thisView.post(() -> {
            params.width = thisView.getWidth();
            view.setLayoutParams(params);
            snackbar.show();
        });

    }


        private boolean haveNetworkConnection () {
            boolean haveConnectedWifi = false;
            boolean haveConnectedMobile = false;

            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] netInfo = cm.getAllNetworkInfo();

            for (NetworkInfo ni : netInfo) {
                if (ni.isConnected()) {
                    if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                        haveConnectedWifi = true;
                        type = ni.getTypeName();
                    } else if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                        haveConnectedMobile = true;
                        type = ni.getTypeName();
                    }
                }
            }

            return haveConnectedWifi || haveConnectedMobile;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        public void checkDatatype () {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PackageManager.PERMISSION_GRANTED);
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                switch (telephonyManager.getDataNetworkType()) {
                    case NETWORK_TYPE_EDGE:
                    case NETWORK_TYPE_GPRS:
                    case NETWORK_TYPE_CDMA:
                    case NETWORK_TYPE_IDEN:
                    case NETWORK_TYPE_1xRTT:
                        dataType = "2G";
                        break;
                    case NETWORK_TYPE_UMTS:
                    case NETWORK_TYPE_HSDPA:
                    case NETWORK_TYPE_HSPA:
                    case NETWORK_TYPE_HSPAP:
                    case NETWORK_TYPE_EVDO_0:
                    case NETWORK_TYPE_EVDO_A:
                    case NETWORK_TYPE_EVDO_B:
                        dataType = "3G";
                        break;
                    case NETWORK_TYPE_LTE:
                        dataType = "4G";
                        break;
                    case NETWORK_TYPE_NR:
                        dataType = "5G";
                        break;
                    default:
                        dataType = "unknown";
                }
            }
        }


    }