package com._99Recharge.customer.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityLoginBinding;
import com._99Recharge.customer.util.CheckInternetConnection;
import com._99Recharge.customer.util.CommonTask;
import com._99Recharge.customer.util.ConstantValues;
import com._99Recharge.customer.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.android.device.DeviceName;
import com.kaopiz.kprogresshud.KProgressHUD;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {
    View thisv;
    ActivityLoginBinding binding;

    LoginViewModel loginViewModel;

    public static String userId = "", pass = "";
     AlertDialog progressDialog;
    KProgressHUD kProgressHUD;
    public static String savedUser="";
    public static String savedPass="";


    Intent intent=null;

    @SuppressLint("HardwareIds")
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

        thisv = findViewById(R.id.activity_login);

        binding.logInBtnId.setOnClickListener(view -> {

            if(CheckInternetConnection.isInternetAvailable(this)) {
                userId = binding.usernameId.getEditText().getText().toString();
                pass = binding.passwordId.getEditText().getText().toString();

                if (SplashActivity.userIDFromDevice.equals(userId)||SplashActivity.userIDFromDevice.equals("")){
                    controlProgressBar(true);
                    loginViewModel.login(userId, pass, CommonTask.getDeviceID(getApplicationContext()));
                }else {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Warning")
                            .setContentText("Invalid user: Your device is already registered to another User")
                            .setConfirmText(" OK ")
                            .show();
                }


            }else {
                Snackbar snackbar=Snackbar.make(view, "Check Internet connection ", Snackbar.LENGTH_SHORT);
                snackbar.setActionTextColor(getResources().getColor(R.color.white));
                snackbar.getView().setBackgroundColor(Color.RED);
                snackbar.show();
            }
        });
        init();
    }


    public void init(){
        binding.rememberMe.setChecked(true);
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
     /*   loginViewModel.getProgress().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.progressBar.setVisibility(integer);
            }
        });*/
        loginViewModel.getLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                controlProgressBar(false);
                if (!s.equals("")) {
                    if (s.equals("0")) {
                        if (binding.rememberMe.isChecked()) {
                            CommonTask.savePreferences(LoginActivity.this, ConstantValues.user.USER_ID, userId);
                            CommonTask.savePreferences(LoginActivity.this, ConstantValues.user.PASSWORD, pass);
                        }else {
                            savedUser=userId;
                            savedPass=pass;
                        }
                        intent = new Intent(LoginActivity.this, PinActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Wrong")
                                .setContentText(s)
                                .setConfirmText("OK")
                                .show();
                    }
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

    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
               kProgressHUD= KProgressHUD.create(LoginActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {
            }
        } else if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
            this.kProgressHUD.dismiss();
        }
    }



}