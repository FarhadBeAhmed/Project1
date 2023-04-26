package com.example.project1.view.ui.BillPayment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.project1.view.ui.DashboardActivity;
import com.example.project1.R;

public class BillPaymentActivity extends AppCompatActivity {


    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_payment);
        toolbar = findViewById(R.id.projectNameId);

        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillPaymentActivity.this, DashboardActivity.class));
            }
        });
        setSupportActionBar(toolbar);
        switchFragment(new BillPayMainPageFragment());
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.bill_pay_fragment_container, fragment, "BillPayMainPageFragment");
        fragmentTransaction.addToBackStack("BillPayMainPageFragment").commit();
    }


}