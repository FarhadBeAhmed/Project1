package com._99Recharge.customer.view.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityResellerBinding;
import com._99Recharge.customer.service.model.responseBody.CommonResponse;
import com._99Recharge.customer.util.CommonTask;
import com._99Recharge.customer.util.ConstantValues;
import com._99Recharge.customer.viewModel.AddResellerViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ResellerActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityResellerBinding binding;
    AddResellerViewModel viewModel;
    String name = "";
    String mobile = "";
    String user = "";
    String pass = "";
    String pin = "";
    String level="";
    RadioButton radioButton;
    KProgressHUD kProgressHUD;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initial();
        binding.submit.setOnClickListener(this);
        binding.mainToolbarId.getRoot().setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        binding.mainToolbarId.getRoot().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResellerActivity.super.onBackPressed();
            }
        });


    }

    private void initial() {
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("5")) {
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.VISIBLE);
            binding.level4.setVisibility(View.VISIBLE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("4")) {
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.VISIBLE);
            binding.level4.setVisibility(View.GONE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("3")) {
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.GONE);
            binding.level4.setVisibility(View.GONE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("2")) {
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.GONE);
            binding.level3.setVisibility(View.GONE);
            binding.level4.setVisibility(View.GONE);
        }
        binding.level1.setSelected(true);
        viewModel = new ViewModelProvider(this).get(AddResellerViewModel.class);
        viewModel.getData().observe(this, new Observer<CommonResponse>() {
            @Override
            public void onChanged(CommonResponse commonResponse) {
                controlProgressBar(false);
                if (commonResponse.getError()==0){
                    new SweetAlertDialog(ResellerActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Successful")
                            .setContentText(commonResponse.getMsg())
                            .setConfirmText(" OK ")
                            .show();
                    binding.name.getEditText().setText("");
                    binding.mobile.getEditText().setText("");
                    binding.user.getEditText().setText("");
                    binding.password.getEditText().setText("");
                    binding.pin.getEditText().setText("");
                }else {
                    new SweetAlertDialog(ResellerActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Failed")
                            .setContentText(commonResponse.getMsg())
                            .setConfirmText(" OK ")
                            .show();
                }

            }
        });

        binding.operatorsId.setOnCheckedChangeListener((group, checkedId) -> {
            radioButton = (RadioButton) findViewById(checkedId);
            if (radioButton.getText().equals("Customer")) level="level1";
            if (radioButton.getText().equals("Reseller")) level="level2";
            if (radioButton.getText().equals("Agent")) level="level3";
            if (radioButton.getText().equals("Admin")) level="level5";


        });


    }

    @Override
    public void onClick(View v) {
        if (CommonTask.validation(binding.name, binding.mobile, binding.user, binding.pin, binding.password)) {
            name = binding.name.getEditText().getText().toString();
            mobile = binding.mobile.getEditText().getText().toString();
            user = binding.user.getEditText().getText().toString();
            pass = binding.password.getEditText().getText().toString();
            pin = binding.pin.getEditText().getText().toString();
            if (!CommonTask.getPreferences(this, ConstantValues.user.USER_ID).equals("")) {
                if (!level.equals("")){
                    controlProgressBar(true);
                    viewModel.callForAddReseller(CommonTask.getPreferences(this, ConstantValues.user.USER_ID),name,"","",mobile,"",user,pass,pin,level);
                }
            }


        }

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