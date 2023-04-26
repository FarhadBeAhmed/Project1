package com.example.project1.allFragmnts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project1.PackagesFragment.RechargeConfirmFragment;
import com.example.project1.R;

public class ComboPacksFragment extends Fragment {

    String operatorName;
    TextView combo;

    public ComboPacksFragment(String operatorName) {
        this.operatorName=operatorName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_combo_packs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        combo=view.findViewById(R.id.comboId);
        combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new RechargeConfirmFragment(),"RechargeConfirmFragment").addToBackStack("RechargeConfirmFragment").commit();
            }
        });



    }
}