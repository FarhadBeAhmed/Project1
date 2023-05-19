package com._99Recharge.view.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com._99Recharge.databinding.ActivityResellerBinding;
import com._99Recharge.util.CommonTask;
import com._99Recharge.util.ConstantValues;

public class ResellerActivity extends AppCompatActivity {
    ActivityResellerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityResellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initial();
    }

    private void initial() {
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("5")){
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.VISIBLE);
            binding.level4.setVisibility(View.VISIBLE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("4")){
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.VISIBLE);
            binding.level4.setVisibility(View.GONE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("3")){
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.VISIBLE);
            binding.level3.setVisibility(View.GONE);
            binding.level4.setVisibility(View.GONE);
        }
        if (CommonTask.getPreferences(getApplicationContext(), ConstantValues.Flexiload.LEVEL).equals("2")){
            binding.level1.setVisibility(View.VISIBLE);
            binding.level2.setVisibility(View.GONE);
            binding.level3.setVisibility(View.GONE);
            binding.level4.setVisibility(View.GONE);
        }
    }
}