package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.project1.adaptars.DashboardPageAdapter;
import com.example.project1.adaptars.HistoryPageAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class HistoryActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private TabItem historyTab, reportTab;
    private ViewPager viewPager;
    private HistoryPageAdapter pageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        tabLayout = findViewById(R.id.dbLayout1);
        historyTab = findViewById(R.id.hTabId);
        reportTab = findViewById(R.id.hReportTabId);
        viewPager = findViewById(R.id.vPager);

        pageAdapter = new HistoryPageAdapter(this.getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1) {
                    pageAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
}