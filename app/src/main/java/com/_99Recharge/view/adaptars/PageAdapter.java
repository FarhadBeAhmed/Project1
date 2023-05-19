package com._99Recharge.view.adaptars;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com._99Recharge.view.ui.allFragmnts.ConnectFragment;
import com._99Recharge.view.ui.allFragmnts.ecardFragment;

public class PageAdapter extends FragmentPagerAdapter {
    int tabCount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){

           case 0: return new ecardFragment();
           case 1: return new ConnectFragment();

           default: return null;

       }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}