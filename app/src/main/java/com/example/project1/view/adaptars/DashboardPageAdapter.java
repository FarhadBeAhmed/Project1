package com.example.project1.view.adaptars;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project1.view.ui.allFragmnts.DashboardFragment;
import com.example.project1.view.ui.allFragmnts.ReportFragment;

public class DashboardPageAdapter extends FragmentPagerAdapter {
    int tabCount;
    public DashboardPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new DashboardFragment();
            case 1: return new ReportFragment();
            default: return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
