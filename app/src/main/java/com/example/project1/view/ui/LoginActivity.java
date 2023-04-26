package com.example.project1.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.RequestParams;
import com.codepath.asynchttpclient.callback.TextHttpResponseHandler;
import com.example.project1.databinding.ActivityLoginBinding;
import com.example.project1.service.FHelper.API;
import com.example.project1.service.FHelper.ConstantValues;
import com.example.project1.service.FHelper.User;
import com.example.project1.R;
import com.example.project1.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.jaredrummler.android.device.DeviceName;
import com.kaopiz.kprogresshud.KProgressHUD;


import okhttp3.Headers;

public class LoginActivity extends AppCompatActivity {

    static API api;
    static User user;
    View thisv;
    ActivityLoginBinding binding;

    LoginViewModel loginViewModel;

    public static String userId = "", pass = "";

    KProgressHUD progressDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());

        DeviceName.init(this);
        String deviceName = DeviceName.getDeviceName();



        api= ConstantValues.getAPI();
        user=new User(getApplicationContext());


        thisv = findViewById(R.id.activity_login);

        binding.logInBtnId.setOnClickListener(view -> {
            loginViewModel.login(binding.usernameId.getEditText().getText().toString(),binding.passwordId.getEditText().getText().toString());
        });
        init();
    }
    public void init(){
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setVisibility(integer);
            }
        });
        loginViewModel.getLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Intent intent=null;
                if (s.equals("0")){
                    intent = new Intent(LoginActivity.this, PinActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                }

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