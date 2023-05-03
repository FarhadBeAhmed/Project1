package com.example.project1.view.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import com.example.project1.databinding.ActivityLoginBinding;
import com.example.project1.service.FHelper.API;
import com.example.project1.service.FHelper.User;
import com.example.project1.R;
import com.example.project1.util.CheckInternetConnection;
import com.example.project1.util.CommonTask;
import com.example.project1.util.ConstantValues;
import com.example.project1.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.jaredrummler.android.device.DeviceName;
import com.kaopiz.kprogresshud.KProgressHUD;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity {
    View thisv;
    ActivityLoginBinding binding;

    LoginViewModel loginViewModel;

    public static String userId = "", pass = "";
     AlertDialog progressDialog;
    KProgressHUD kProgressHUD;


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
                    CommonTask.snackBar(getApplicationContext(), findViewById(R.id.splashId),"Invalid user: Your device is already registered to another User");
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
                if (s.equals("0")){
                    CommonTask.savePreferences(LoginActivity.this, ConstantValues.user.USER_ID,userId);
                    CommonTask.savePreferences(LoginActivity.this, ConstantValues.user.PASSWORD,pass);
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