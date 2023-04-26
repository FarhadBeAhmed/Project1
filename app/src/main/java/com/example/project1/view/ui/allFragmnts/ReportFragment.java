package com.example.project1.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.project1.view.ui.AllReports.AllReportsActivity;
import com.example.project1.R;


public class ReportFragment extends Fragment implements View.OnClickListener {

    RelativeLayout trx_history;
    RelativeLayout sale_Report;
    RelativeLayout recharge_history;


    public ReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        trx_history = view.findViewById(R.id.trxHistoryLayoutId);
        sale_Report = view.findViewById(R.id.salesReportLayoutId);
        recharge_history = view.findViewById(R.id.rHistoryLayoutId);

        trx_history.setOnClickListener(this);
        sale_Report.setOnClickListener(this);
        recharge_history.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.trxHistoryLayoutId:
                intent = new Intent(getContext(), AllReportsActivity.class);
                intent.putExtra("fragmentValue", 1);
                startActivity(intent);
                break;
            case R.id.salesReportLayoutId:
                intent = new Intent(getContext(), AllReportsActivity.class);
                intent.putExtra("fragmentValue", 2);
                startActivity(intent);
                break;
            case R.id.rHistoryLayoutId:
                intent = new Intent(getContext(), AllReportsActivity.class);
                intent.putExtra("fragmentValue", 3);
                startActivity(intent);
                break;
        }
    }
}