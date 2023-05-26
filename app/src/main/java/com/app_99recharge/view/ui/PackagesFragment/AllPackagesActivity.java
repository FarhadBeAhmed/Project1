package com.app_99recharge.view.ui.PackagesFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app_99recharge.R;
import com.app_99recharge.databinding.ActivityAllPackagesBinding;
import com.app_99recharge.view.ui.DashboardActivity;
import com.app_99recharge.view.ui.allFragmnts.ComboPacksFragment;
import com.app_99recharge.view.ui.allFragmnts.DataPacksFragment;
import com.app_99recharge.view.ui.allFragmnts.VoicePacksFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class AllPackagesActivity extends AppCompatActivity {

    public static String val;




    String operatorName;

    ActivityAllPackagesBinding binding;

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, DashboardActivity.class));

    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllPackagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.allPackToolbarId1.getRoot().setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back));
        binding.allPackToolbarId1.getRoot().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllPackagesActivity.this, DashboardActivity.class));
            }
        });
        setSupportActionBar(binding.allPackToolbarId1.toolbarID);
        val = getIntent().getStringExtra("data");
        binding.gp1LayoutId.setOnClickListener(this::gpBtnClicked);
        binding.robi1LayoutId.setOnClickListener(this::robiBtnClicked);
        binding.airtel1LayoutId.setOnClickListener(this::airtelBtnClicked);
        binding.banglaLink1LayoutId.setOnClickListener(this::blBtnClicked);
        binding.teletalk1LayoutId.setOnClickListener(this::telBtnClicked);
        if (val != null) {
            switch (val) {
                case "Grameen":
                   // switchFragment(new GpPackegesFragment(), "gpPack");
                    binding.allPackToolbarId1.textView4.setText("GrameenPhone");
                    setSupportActionBar(binding.allPackToolbarId1.toolbarID);
                    binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#ffffffff"));
                    binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));

                    break;
                case "Robi":
                    binding.allPackToolbarId1.textView4.setText("Robi");
                    setSupportActionBar(binding.allPackToolbarId1.toolbarID);

                   // switchFragment(new RobiPackegesFragment(), "robiPack");
                    binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
                    binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;
                case "Banglalink":
                    binding.allPackToolbarId1.textView4.setText("Banglalink");
                    setSupportActionBar(binding.allPackToolbarId1.toolbarID);
                  //  switchFragment(new BlPackegesFragment(), "blPack");
                    binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
                    binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;
                case "Airtel":
                    binding.allPackToolbarId1.textView4.setText("Airtel");
                    setSupportActionBar(binding.allPackToolbarId1.toolbarID);
                   // switchFragment(new airPackagesFragment(), "airPack");
                    binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
                    binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
                    break;

            }
        }
        initial();

    }

    private void initial() {
        switchFragment(new ComboPacksFragment(),val);
       // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ComboPacksFragment("air"),"air").addToBackStack("air").commit();


        binding.packTabLayout1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        switchFragment(new ComboPacksFragment(),val);
                        break;
                    case 1:
                        switchFragment(new DataPacksFragment(),val);
                        // getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DataPacksFragment(),"data").addToBackStack("data").commit();

                        break;
                    case 2:
                        switchFragment(new VoicePacksFragment(),val);
                      //  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new VoicePacksFragment(),"voice").addToBackStack("voice").commit();

                        break;


                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void telBtnClicked(View view) {
    }

    @SuppressLint("SetTextI18n")
    private void blBtnClicked(View view) {
        val="Banglalink";
        initial();
        Objects.requireNonNull(binding.packTabLayout1.getTabAt(0)).select();
        binding.allPackToolbarId1.textView4.setText("Banglalink");
        setSupportActionBar(binding.allPackToolbarId1.toolbarID);
        //switchFragment(new BlPackegesFragment(), "blPack");
        binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    @SuppressLint("SetTextI18n")
    private void airtelBtnClicked(View view) {
        val="Airtel";
        initial();
        Objects.requireNonNull(binding.packTabLayout1.getTabAt(0)).select();

        binding.allPackToolbarId1.textView4.setText("Airtel");
        setSupportActionBar(binding.allPackToolbarId1.toolbarID);
        //switchFragment(new airPackagesFragment(), "airPack");
        binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    private void robiBtnClicked(View view) {
        val="Robi";
        initial();
        Objects.requireNonNull(binding.packTabLayout1.getTabAt(0)).select();
        binding.allPackToolbarId1.textView4.setText("Robi");
        setSupportActionBar(binding.allPackToolbarId1.toolbarID);
        //switchFragment(new RobiPackegesFragment(), "robiPack");
        binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#ffffff"));
        binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
    }

    @SuppressLint("SetTextI18n")
    private void gpBtnClicked(View view) {
        val="Grameen";
        initial();
        Objects.requireNonNull(binding.packTabLayout1.getTabAt(0)).select();
       // switchFragment(new GpPackegesFragment(), "gpPack");
        binding.allPackToolbarId1.textView4.setText("GrameenPhone");
        setSupportActionBar(binding.allPackToolbarId1.toolbarID);
        binding.gp1LayoutId.setBackgroundColor(Color.parseColor("#ffffffff"));
        binding.robi1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.airtel1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.banglaLink1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));
        binding.teletalk1LayoutId.setBackgroundColor(Color.parseColor("#F5E8E8E8"));

    }


    private void switchFragment(Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.main_home_contain, fragment, tag);
        fragmentTransaction.addToBackStack(tag).commit();
    }


}