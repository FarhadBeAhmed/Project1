package com._99Recharge.customer.view.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com._99Recharge.R;
import com._99Recharge.customer.service.FHelper.User;
import com._99Recharge.customer.view.adaptars.DashboardPageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {


    Toolbar toolbar;

    User user;

    private TabLayout tabLayout2;
    private TabItem dashboardTab, reportTab;
    private ViewPager viewPager;
    private DashboardPageAdapter pageAdapter2;
    View thisView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        user=new User();

        tabLayout2 = findViewById(R.id.dbLayout1);
        dashboardTab = findViewById(R.id.dbTabId);
        reportTab = findViewById(R.id.reportTabId);
        viewPager = findViewById(R.id.vpagger2);
        toolbar = findViewById(R.id.ToolbarId);
        thisView = findViewById(R.id.parentView);


        setSupportActionBar(toolbar);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_list_menu));

        pageAdapter2 = new DashboardPageAdapter(this.getSupportFragmentManager(), tabLayout2.getTabCount());
        viewPager.setAdapter(pageAdapter2);

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1) {
                    pageAdapter2.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logoutId:
                finishAffinity();
                startActivity(new Intent(this, PinActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}