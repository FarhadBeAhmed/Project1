package com.example.project1.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import com.example.project1.R;
import com.example.project1.databinding.ActivityRechargeHistoryBinding;
import com.example.project1.service.model.responseBody.RechargeHistoryData;
import com.example.project1.service.model.responseBody.RechargeHistoryResponse;
import com.example.project1.util.CheckInternetConnection;
import com.example.project1.util.CommonTask;
import com.example.project1.util.ConstantValues;
import com.example.project1.view.adaptars.PackagesAdapter;
import com.example.project1.view.adaptars.RechargeHistoryAdapter;
import com.example.project1.viewModel.PackagesViewModel;
import com.example.project1.viewModel.RechargeHistoryViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RechargeHistoryActivity extends AppCompatActivity {

    ActivityRechargeHistoryBinding binding;
    KProgressHUD kProgressHUD;
    RechargeHistoryViewModel historyViewModel;
    ArrayList<RechargeHistoryData> historyResponseList = new ArrayList<>();
    RechargeHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRechargeHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initial();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!CommonTask.getPreferences(RechargeHistoryActivity.this, ConstantValues.user.USER_ID).isEmpty()) {
            if (CheckInternetConnection.isInternetAvailable(this)) {
                controlProgressBar(true);
                historyViewModel.getRecharges(CommonTask.getPreferences(RechargeHistoryActivity.this, ConstantValues.user.USER_ID));
            } else {
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setContentText("Please Check Internet Connection!")
                        .setConfirmText("ok")
                        .show();
            }
        } else if (!LoginActivity.userId.equals("")) {
            controlProgressBar(true);
            historyViewModel.getRecharges(LoginActivity.userId);
        }


    }


    @SuppressLint("NotifyDataSetChanged")
    private void initial() {
        binding.rechargeHistoryRecView.setLayoutManager(new LinearLayoutManager(RechargeHistoryActivity.this));
        adapter = new RechargeHistoryAdapter(RechargeHistoryActivity.this, historyResponseList, R.layout.recharge_history_single_row);
        binding.rechargeHistoryRecView.setAdapter(adapter);


        historyViewModel = new ViewModelProvider(this).get(RechargeHistoryViewModel.class);
        historyViewModel.getData().observe(this, packageResponse -> {
            historyResponseList.addAll(packageResponse.getData());
            adapter.notifyDataSetChanged();
            controlProgressBar(false);
        });

    }

    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD = KProgressHUD.create(RechargeHistoryActivity.this)
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