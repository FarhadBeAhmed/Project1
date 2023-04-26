package com.example.project1.PackagesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.R;
import com.example.project1.adaptars.PackegesPageAdapter;
import com.example.project1.allFragmnts.ComboPacksFragment;
import com.example.project1.allFragmnts.DataPacksFragment;
import com.example.project1.allFragmnts.VoicePacksFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class RobiPackegesFragment extends Fragment {
    TabLayout tabLayout;
    TabItem comboTab, dataTab, voiceTab;
    private ViewPager viewPager;
    private PackegesPageAdapter pageAdapter;
    protected Toolbar toolbar;
    int whatTapOpen;


    public RobiPackegesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_robi_packeges, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.packTabLayout1);
        comboTab = view.findViewById(R.id.comboTabId1);
        dataTab = view.findViewById(R.id.dataTabId1);
        voiceTab = view.findViewById(R.id.voiceTabId1);
        viewPager = view.findViewById(R.id.vpagger2);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ComboPacksFragment("robi"),"robi").addToBackStack("gp").commit();

     /*   pageAdapter = new PackegesPageAdapter(getChildFragmentManager(), tabLayout.getTabCount(),"robi");
        whatTapOpen=0;
        TabAdapter tabAdapter = new TabAdapter(requireActivity().getSupportFragmentManager());
        tabAdapter.add(new ComboPacksFragment("robi"), "Combo");
        tabAdapter.add(new DataPacksFragment(), "Data");
        tabAdapter.add(new VoicePacksFragment(), "Voice");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(whatTapOpen == 0 ? 0 : whatTapOpen == 1 ? 1 : whatTapOpen == 2 ? 2 : whatTapOpen == 3 ? 3 : 4).select();
        tabLayout.getTabAt(whatTapOpen).select();*/

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ComboPacksFragment("robi"),"robi").addToBackStack("robi").commit();
                        break;
                    case 1:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new DataPacksFragment(),"data").addToBackStack("data").commit();

                        break;
                    case 2:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new VoicePacksFragment(),"voice").addToBackStack("voice").commit();

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
}