package com.app_99recharge.view.adaptars;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.app_99recharge.view.ui.allFragmnts.HistoryFragment;
import com.app_99recharge.view.ui.allFragmnts.ReportFragment;

public class HistoryPageAdapter extends FragmentPagerAdapter {
    int tabCount;
    public HistoryPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new HistoryFragment();
            case 1: return new ReportFragment();
            default: return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
