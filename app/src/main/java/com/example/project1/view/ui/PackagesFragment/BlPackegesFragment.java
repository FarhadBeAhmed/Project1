package com.example.project1.view.ui.PackagesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.R;
import com.example.project1.view.ui.allFragmnts.ComboPacksFragment;
import com.example.project1.view.ui.allFragmnts.DataPacksFragment;
import com.example.project1.view.ui.allFragmnts.VoicePacksFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class BlPackegesFragment extends Fragment {
    TabLayout tabLayout;
    TabItem comboTab, dataTab, voiceTab;
    protected Toolbar toolbar;

    public BlPackegesFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bl_packeges, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.packTabLayout1);
        comboTab = view.findViewById(R.id.comboTabId1);
        dataTab = view.findViewById(R.id.dataTabId1);
        voiceTab = view.findViewById(R.id.voiceTabId1);
        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ComboPacksFragment("bl"),"bl").addToBackStack("bl").commit();


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch(tab.getPosition()) {
                    case 0:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ComboPacksFragment("bl"),"bl").addToBackStack("bl").commit();
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