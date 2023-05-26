package com._99Recharge.customer.view.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityChangePassPinBinding;
import com._99Recharge.customer.service.model.responseBody.CommonResponse;
import com._99Recharge.customer.viewModel.UpdatePassPinViewModel;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class changePassPinActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityChangePassPinBinding binding;
    UpdatePassPinViewModel passPinViewModel;
    private String oldNumber = "";
    private String newNumber = "";
    private String confirmNumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChangePassPinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.pinSubmit.setOnClickListener(this);
        binding.passSubmit.setOnClickListener(this);
        binding.flexiloadToolbarId.getRoot().setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        binding.flexiloadToolbarId.getRoot().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               changePassPinActivity.super.onBackPressed();
            }
        });

        initial();

    }

    private void initial() {
        passPinViewModel = new ViewModelProvider(this).get(UpdatePassPinViewModel.class);

        passPinViewModel.getData().observe(this, new Observer<CommonResponse>() {
            @Override
            public void onChanged(CommonResponse response) {
                if (response.getError() == 0) {
                    new SweetAlertDialog(changePassPinActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("SuccessFul")
                            .setContentText(response.getMsg())
                            .setConfirmText("OK")
                            .show();
                } else {
                    new SweetAlertDialog(changePassPinActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Failed")
                            .setContentText(response.getMsg())
                            .setConfirmText("OK")
                            .show();
                }


            }
        });

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pinSubmit:
                oldNumber = binding.oldPinId.getText().toString();
                newNumber = binding.newPinId.getText().toString();
                confirmNumber = binding.confirmPinId.getText().toString();
                if (!oldNumber.equals("") && !newNumber.equals("") && !confirmNumber.equals("")) {
                    changePinPass("pin");
                }

                break;
            case R.id.passSubmit:
                oldNumber = binding.oldPassId.getText().toString();
                newNumber = binding.newPassId.getText().toString();
                confirmNumber = binding.confirmPassId.getText().toString();
                if (!oldNumber.equals("") && !newNumber.equals("") && !confirmNumber.equals("")) {
                    changePinPass("pass");
                }

                break;
            default:
        }

    }

    private void changePinPass(String type) {
        passPinViewModel.updatePinPass(oldNumber, newNumber, confirmNumber, type);
    }


}