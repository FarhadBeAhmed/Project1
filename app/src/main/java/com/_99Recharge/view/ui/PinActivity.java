package com._99Recharge.view.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityPinBinding;
import com._99Recharge.service.ModelClasses.Pin;
import com._99Recharge.service.model.responseBody.PinChkResponse;
import com._99Recharge.util.CheckInternetConnection;
import com._99Recharge.util.CommonTask;
import com._99Recharge.util.ConstantValues;
import com._99Recharge.viewModel.LoginViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Objects;

public class PinActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPinBinding binding;
    View thisView;
    LoginViewModel loginViewModel;
    KProgressHUD kProgressHUD;
    public static Pin pinNumber=new Pin();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        thisView= findViewById(R.id.pinActivity);
        binding.pinBtn.setOnClickListener(this);
        init();

    }

    public void init(){
        loginViewModel= new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getPin().observe(this, new Observer<PinChkResponse>() {
            @Override
            public void onChanged(PinChkResponse response) {

                controlProgressBar(false);
                Intent intent;
                if (response.getError()==0){
                    pinNumber.setPin(Objects.requireNonNull(binding.pinEditTextId.getText()).toString());
                    pinNumber.setTemp_pin(response.getTemp_pin());
                    intent = new Intent(PinActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }else {
                    Toast.makeText(PinActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.pinBtn){

            if (!CommonTask.getPreferences(PinActivity.this, ConstantValues.user.USER_ID).isEmpty()){
                if(CheckInternetConnection.isInternetAvailable(this)) {
                    controlProgressBar(true);
                    loginViewModel.pin(CommonTask.getPreferences(PinActivity.this, ConstantValues.user.USER_ID),binding.pinEditTextId.getText().toString(),"0");

                }else {
                    Snackbar snackbar=Snackbar.make(view, "Check Internet connection ", Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(getResources().getColor(R.color.white));
                    snackbar.getView().setBackgroundColor(Color.RED);
                    snackbar.show();
                }
            }else if (!LoginActivity.userId.equals("")){
                controlProgressBar(true);
                loginViewModel.pin(LoginActivity.userId,binding.pinEditTextId.getText().toString(),"0");

            }
        }
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
                kProgressHUD= KProgressHUD.create(this)
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