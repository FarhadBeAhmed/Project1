package com.example.project1.view.adaptars;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.project1.view.ui.allFragmnts.ComboPacksFragment;
import com.example.project1.view.ui.allFragmnts.DataPacksFragment;
import com.example.project1.view.ui.allFragmnts.VoicePacksFragment;

public class PackegesPageAdapter extends FragmentPagerAdapter {
    int tabCount;
    String operator;
    public PackegesPageAdapter(@NonNull FragmentManager fm, int behavior,String operator) {
        super(fm, behavior);
        tabCount=behavior;
        this.operator=operator;


    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ComboPacksFragment(operator);
            case 1: return new DataPacksFragment();
            case 2: return new VoicePacksFragment();
            default: return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
