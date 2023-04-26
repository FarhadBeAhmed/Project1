package com.example.project1.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.project1.view.ui.BillPayment.BillPaymentActivity;
import com.example.project1.view.ui.MBanking.Bkash.BkashActivity;
import com.example.project1.view.ui.changePassPinActivity;
import com.example.project1.view.ui.MobileRecharge.FlexiloadActivity;
import com.example.project1.view.ui.MBanking.Nagad.NagadActivity;
import com.example.project1.R;
import com.example.project1.view.ui.MBanking.Rocket.RocketActivity;


public class HistoryFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout banglalinkBtn;
    private RelativeLayout bkashBtn;
    private RelativeLayout rocketBtn;
    private RelativeLayout nagadBtn;
    private RelativeLayout bangladeshBtn;
    private RelativeLayout billPaymentBtn;
    private RelativeLayout callingCardBtn;
    private RelativeLayout flaxiloadBtn;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        flaxiloadBtn = view.findViewById(R.id.flaxiLayoutId);
        banglalinkBtn = view.findViewById(R.id.blLayoutId);
        bkashBtn = view.findViewById(R.id.bkshLayoutId);
        rocketBtn = view.findViewById(R.id.rocketLayoutId);
        nagadBtn = view.findViewById(R.id.NagadLayoutId);
        bangladeshBtn = view.findViewById(R.id.bdeshLayoutId);
        billPaymentBtn = view.findViewById(R.id.billLayoutId);
        callingCardBtn = view.findViewById(R.id.callingLayoutId);


        flaxiloadBtn.setOnClickListener(this);
        banglalinkBtn.setOnClickListener(this);
        bkashBtn.setOnClickListener(this);
        rocketBtn.setOnClickListener(this);
        nagadBtn.setOnClickListener(this);
        bangladeshBtn.setOnClickListener(this);
        billPaymentBtn.setOnClickListener(this);
        callingCardBtn.setOnClickListener(this);

//        flaxiloadBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent  intent = new Intent(getContext(), FlexiloadActivity.class);
//                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        });


        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        Intent intent;


        int id = view.getId();
        if (id == R.id.flaxiLayoutId) {
            intent = new Intent(getContext(), FlexiloadActivity.class);
            startActivity(intent);


        } else if (id == R.id.bkshLayoutId) {
            intent = new Intent(getContext(), BkashActivity.class);
            startActivity(intent);


        } else if (id == R.id.rocketLayoutId) {
            intent = new Intent(getContext(), RocketActivity.class);
            startActivity(intent);


        } else if (id == R.id.NagadLayoutId) {
            intent = new Intent(getContext(), NagadActivity.class);
            startActivity(intent);


        } else if (id == R.id.billLayoutId) {
            intent = new Intent(getContext(), BillPaymentActivity.class);
            startActivity(intent);


        } else if (id == R.id.callingLayoutId) {
            intent = new Intent(getContext(), changePassPinActivity.class);
            startActivity(intent);
        }

    }
}