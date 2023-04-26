package com.example.project1.adaptars;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project1.allFragmnts.ConnectFragment;
import com.example.project1.allFragmnts.FlexiLoadFragment;
import com.example.project1.allFragmnts.ecardFragment;
import com.example.project1.allFragmnts.logInFragment;

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
           case 0: return new logInFragment();
           case 1: return new ecardFragment();
           case 2: return new ConnectFragment();

           default: return null;

       }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
