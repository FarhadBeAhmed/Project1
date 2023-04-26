package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.example.project1.FHelper.API;
import com.example.project1.FHelper.ConstantValues;
import com.example.project1.FHelper.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jaredrummler.android.device.DeviceName;


import okhttp3.Headers;

public class LoginActivity extends AppCompatActivity {

    static API api;
    static User user;
    AppCompatButton login;
    TextInputLayout username, password;
    CheckBox rememberMe;
    View thisv;

    public static String userId = "", pass = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        DeviceName.init(this);
        String deviceName = DeviceName.getDeviceName();


        api= ConstantValues.getAPI();
        user=new User(getApplicationContext());


        login = findViewById(R.id.logInBtnId);
        username = findViewById(R.id.usernameId);
        password = findViewById(R.id.passwordId);
        rememberMe = findViewById(R.id.rememberMe);

        thisv = findViewById(R.id.activity_login);

        login.setOnClickListener(view -> {
            startActivity(new Intent(this, PinActivity.class));
        });
    }
    public void login(){

        AsyncHttpClient client = new AsyncHttpClient();
        //        client.setSSLSocketFactory(MySSLSocketFactory.getFixedSocketFactory());
       // client.setEnableRedirects(true);


        RequestParams params = new RequestParams();

        params.put("username", userId);
        params.put("password", pass);

        //SSL csertificate er problem silo. er jonno api cl nissilo nah.
        client.get("http://www.load16.com/app/r_login.php",params,new TextHttpResponseHandler()  {

            @Override
            public void onSuccess(int statusCode, Headers headers, String response) {
                Snackbar.make(thisv, response, Snackbar.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(int statusCode, @Nullable Headers headers, String errorResponse, @Nullable Throwable throwable) {
                Snackbar.make(thisv, errorResponse, Snackbar.LENGTH_SHORT).show();

            }


        });

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            }
            assert imm != null;
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

}