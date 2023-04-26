package com.example.project1.PackagesFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.project1.DashboardActivity;
import com.example.project1.R;

public class AllPackagesActivity extends AppCompatActivity {
    RelativeLayout gpBtn;
    RelativeLayout robiBtn;
    RelativeLayout airtelBtn;
    RelativeLayout blBtn;
    RelativeLayout telBtn;
    String val;
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));
      /*  if (getSupportFragmentManager().getBackStackEntryCount() == 2) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            getSupportFragmentManager().popBackStack();
        }*/
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_packages);
        gpBtn = findViewById(R.id.gp1LayoutId);
        robiBtn = findViewById(R.id.robi1LayoutId);
        airtelBtn = findViewById(R.id.airtel1LayoutId);
        blBtn = findViewById(R.id.banglaLink1LayoutId);
        telBtn = findViewById(R.id.teletalk1LayoutId);
        toolbar = findViewById(R.id.allPackToolbarId1);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllPackagesActivity.this, DashboardActivity.class));
            }
        });
        setSupportActionBar(toolbar);
        val = getIntent().getStringExtra("data");
        gpBtn.setOnClickListener(this::gpBtnClicked);
        robiBtn.setOnClickListener(this::robiBtnClicked);
        airtelBtn.setOnClickListener(this::airtelBtnClicked);
        blBtn.setOnClickListener(this::blBtnClicked);
        telBtn.setOnClickListener(this::telBtnClicked);


        if (val != null) {
            switch (val) {
                case "gp":
                    switchFragment(new GpPackegesFragment(), "gpPack");
                    toolbar.setTitle("GrameenPhone");
                    setSupportActionBar(toolbar);
                    gpBtn.setBackgroundColor(Color.parseColor("#ffffffff"));
                    robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));

                    break;
                case "robi":
                    toolbar.setTitle("Robi");
                    setSupportActionBar(toolbar);
                    switchFragment(new RobiPackegesFragment(), "robiPack");
                    robiBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                    gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;
                case "bl":
                    toolbar.setTitle("Banglalink");
                    setSupportActionBar(toolbar);
                    switchFragment(new BlPackegesFragment(), "blPack");
                    blBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                    gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;
                case "air":
                    toolbar.setTitle("Airtel");
                    setSupportActionBar(toolbar);
                    switchFragment(new airPackagesFragment(), "airPack");
                    airtelBtn.setBackgroundColor(Color.parseColor("#ffffff"));
                    gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;

            }
        }

    }

    private void telBtnClicked(View view) {
    }

    private void blBtnClicked(View view) {
        toolbar.setTitle("Banglalink");
        setSupportActionBar(toolbar);
        switchFragment(new BlPackegesFragment(), "blPack");
        blBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    private void airtelBtnClicked(View view) {
        toolbar.setTitle("Airtel");
        setSupportActionBar(toolbar);
        switchFragment(new airPackagesFragment(), "airPack");
        airtelBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    private void robiBtnClicked(View view) {
        toolbar.setTitle("Robi");
        setSupportActionBar(toolbar);
        switchFragment(new RobiPackegesFragment(), "robiPack");
        robiBtn.setBackgroundColor(Color.parseColor("#ffffff"));
        gpBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    private void gpBtnClicked(View view) {
        switchFragment(new GpPackegesFragment(), "gpPack");
        toolbar.setTitle("GrameenPhone");
        setSupportActionBar(toolbar);
        gpBtn.setBackgroundColor(Color.parseColor("#ffffffff"));
        robiBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        airtelBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        blBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        telBtn.setBackgroundColor(Color.parseColor("#F5E8E8E8"));

    }


    private void switchFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_home_contain, fragment, tag);
        fragmentTransaction.addToBackStack(tag).commit();
    }


}