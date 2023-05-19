package com._99Recharge.view.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com._99Recharge.R;
import com._99Recharge.databinding.ActivityRechargeHistoryBinding;
import com._99Recharge.service.model.responseBody.RechargeHistoryData;
import com._99Recharge.util.CheckInternetConnection;
import com._99Recharge.util.CommonTask;
import com._99Recharge.util.ConstantValues;
import com._99Recharge.view.adaptars.RechargeHistoryAdapter;
import com._99Recharge.viewModel.RechargeHistoryViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

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
            controlProgressBar(false);
            if (packageResponse.getData()!=null){
                binding.notFound.setVisibility(View.GONE);
                historyResponseList.addAll(packageResponse.getData());
                adapter.notifyDataSetChanged();
            }else {
                binding.rechargeHistoryRecView.setVisibility(View.GONE);

            }


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