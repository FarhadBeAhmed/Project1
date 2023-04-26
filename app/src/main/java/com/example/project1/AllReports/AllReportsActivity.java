package com.example.project1.AllReports;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.project1.DashboardActivity;
import com.example.project1.R;

public class AllReportsActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reports);

        int frValue=getIntent().getIntExtra("fragmentValue",0);
        if(frValue==1){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.report_Fragment_Frame_Id,new TransactionHistoryFragment(),"TransactionHistoryFragment")
                    .addToBackStack("TransactionHistoryFragment")
                    .commit();
        }else if (frValue==2){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.report_Fragment_Frame_Id,new SalesReportFragment(),"SalesReportFragment")
                    .addToBackStack("SalesReportFragment")
                    .commit();
        }else if (frValue==3){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.report_Fragment_Frame_Id,new RechargeHistoryFragment(),"RechargeHistoryFragment")
                    .addToBackStack("RechargeHistoryFragment")
                    .commit();
        }

    }
}