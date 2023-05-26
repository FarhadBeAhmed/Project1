package com._99Recharge.customer.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityDomainConnectBinding;
import com._99Recharge.customer.service.CommonUrl;
import com._99Recharge.customer.util.CommonTask;
import com._99Recharge.customer.util.ConstantValues;
import com._99Recharge.customer.viewModel.DomainChkViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DomainConnectActivity extends AppCompatActivity implements View.OnClickListener {


    View connectPage;
    int urlMatchStatus = 0;
    String deviceID="";
    String domainUrl ="";
    ActivityDomainConnectBinding binding;
    DomainChkViewModel domainChkViewModel;
    KProgressHUD kProgressHUD;


    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDomainConnectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectPage = findViewById(R.id.ConnectActivity);

        binding.contBtn2.setOnClickListener(this);

        deviceID= Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        binding.domainNameEditTextId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (Patterns.WEB_URL.matcher(Objects.requireNonNull( binding.domainNameEditTextId.getText()).toString()).matches()) {
                    urlMatchStatus = 1;
                    binding.domainNameEditTextId.setError(null);
                } else {
                    // otherwise show error of invalid url
                    binding.domainNameEditTextId.setError("Invalid Url");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        domainChkViewModel=new ViewModelProvider(this).get(DomainChkViewModel.class);

        initial();
    }

    private void initial() {
        domainChkViewModel.getData().observe(this,response -> {
            Intent intent=null;
            if (response.getError()==0){
                controlProgressBar(false);
                CommonTask.savePreferences(DomainConnectActivity.this, ConstantValues.DOMAIN,domainUrl);
                CommonTask.savePreferences(DomainConnectActivity.this, ConstantValues.user.USER_ID,"");
                CommonTask.savePreferences(DomainConnectActivity.this, ConstantValues.user.PASSWORD,"");
                intent = new Intent(DomainConnectActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }else {
                controlProgressBar(false);
                new SweetAlertDialog(DomainConnectActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Wrong")
                        .setContentText(response.getMsg())
                        .setConfirmText("OK")
                        .show();
            }
        });
    }


    @Override
    public void onClick(View view) {
        domainUrl = binding.domainNameEditTextId.getText().toString();
        int id=view.getId();
        if (id == R.id.contBtn2) {
            domainUrl = binding.domainNameEditTextId.getText().toString();

            /*String subUrl= domainUrl.substring(0,4);


            if(subUrl.equals("www.")){
                domainUrl=domainUrl.substring(4);
            }
*/

            if (!domainUrl.equals("") && urlMatchStatus==1) {
                controlProgressBar(true);
                CommonUrl.BASE_URL= "https://"+domainUrl;
               domainChkViewModel.callForDomainChk(domainUrl);
            }else {
                Snackbar.make(connectPage,"Invalid Domain Name !!",Snackbar.LENGTH_SHORT).show();

            }
        }

    }

    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD= KProgressHUD.create(DomainConnectActivity.this)
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