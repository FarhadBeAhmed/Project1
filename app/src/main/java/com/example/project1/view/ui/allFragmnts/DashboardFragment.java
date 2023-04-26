package com.example.project1.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.project1.service.FHelper.API;
import com.example.project1.service.FHelper.ConstantValues;
import com.example.project1.service.FHelper.User;
import com.example.project1.MySingleton;
import com.example.project1.view.ui.PackagesFragment.AllPackagesActivity;

import com.example.project1.view.ui.MBanking.Bkash.BkashActivity;
import com.example.project1.view.ui.BillPayment.BillPaymentActivity;
import com.example.project1.view.ui.MBanking.Nagad.NagadActivity;
import com.example.project1.view.ui.MBanking.Rocket.RocketActivity;
import com.example.project1.view.ui.changePassPinActivity;
import com.example.project1.view.ui.MobileRecharge.FlexiloadActivity;
import com.example.project1.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DashboardFragment extends Fragment implements View.OnClickListener {


     User user;
     API api;

    TextView txtMarquee, ProfileName;
    TextView net_bal, user_id;
    SwipeRefreshLayout refreshLayout;

    RelativeLayout banglalinkBtn;
    RelativeLayout mBankingBtn;
    RelativeLayout addBalBtn;
    RelativeLayout billPaymentBtn;
    RelativeLayout callingCardBtn;
    RelativeLayout flaxiloadBtn;
    View thisView;
    String userID = "", password = "";

    BottomSheetDialog bottomSheetDialogForOperator, viewForMBanking;

    LinearLayout gpButton;
    LinearLayout robiButton;
    LinearLayout blButton;
    LinearLayout airtelButton;
    LinearLayout teletalkButton;

    RelativeLayout bkashButton;
    RelativeLayout rcketButton;
    RelativeLayout nagadButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        flaxiloadBtn = view.findViewById(R.id.flaxiLayoutId);
        banglalinkBtn = view.findViewById(R.id.blLayoutId);
        mBankingBtn = view.findViewById(R.id.mBankingLayoutId);

        addBalBtn = view.findViewById(R.id.addBalLayoutId);
        billPaymentBtn = view.findViewById(R.id.billLayoutId);
        callingCardBtn = view.findViewById(R.id.callingLayoutId);
        net_bal = view.findViewById(R.id.net_balId);
        user_id = view.findViewById(R.id.ID_profile_name);
        refreshLayout = view.findViewById(R.id.reloadId);

        ProfileName = view.findViewById(R.id.dashboard_profile_name);

        user = new User();
        api = ConstantValues.getAPI();
        thisView = view.findViewById(R.id.fullView);

        flaxiloadBtn.setOnClickListener(this);
        banglalinkBtn.setOnClickListener(this);
        mBankingBtn.setOnClickListener(this);

        addBalBtn.setOnClickListener(this);
        billPaymentBtn.setOnClickListener(this);
        callingCardBtn.setOnClickListener(this);

        txtMarquee = view.findViewById(R.id.marqueeTextId);
        txtMarquee.setSelected(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfo();
            }
        });

        getInfo();


        return view;
    }

    @SuppressLint("SetTextI18n")
    private void getInfo() {
        MySingleton.getInstance(getContext()).addToRequestQueue(api.home_info("test",1234,response -> {
            try {
                if (response.getInt(ConstantValues.ERROR)==0){
                    user_id.setText(response.getString("user_id"));
                    net_bal.setText(response.getString("balance"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }));


        refreshLayout.setRefreshing(false);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.flaxiLayoutId:
                startActivity(new Intent(getContext(), FlexiloadActivity.class));
                break;
            case R.id.addBalLayoutId:
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("Won't be able to recover this file!")
                        .setConfirmText("Yes,delete it!")
                        .show();
               // startActivity(new Intent(getContext(), AddBalanceActivity.class));
                break;
            case R.id.blLayoutId:
                showBottomSheetDialog();
                break;
            case R.id.mBankingLayoutId:
                showBottomSheetDialogForMbanking();
                //startActivity(new Intent(getContext(), MBankingActivity.class));
                break;

            case R.id.billLayoutId:
                startActivity(new Intent(getContext(), BillPaymentActivity.class));
                break;
            case R.id.callingLayoutId:
                startActivity(new Intent(getContext(), changePassPinActivity.class));
                break;
            case R.id.gpLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "gp");
                //intent= new Intent(getContext(), GPPackageActivity.class);
                startActivity(intent);
                break;
            case R.id.robiLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "robi");
                startActivity(intent);
                break;
            case R.id.BLLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "bl");
                startActivity(intent);
                break;
            case R.id.airLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "air");
                startActivity(intent);
                break;
            case R.id.telLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "tel");
                startActivity(intent);
                break;
            case R.id.bkashLayoutId:
                viewForMBanking.cancel();
                intent = new Intent(getContext(), BkashActivity.class);
                startActivity(intent);
                break;
            case R.id.rocketLayoutId:
                viewForMBanking.cancel();
                intent = new Intent(getContext(), RocketActivity.class);
                startActivity(intent);
                break;
            case R.id.nagadLayoutId:
                viewForMBanking.cancel();
                intent = new Intent(getContext(), NagadActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void showBottomSheetDialog() {

        bottomSheetDialogForOperator = new BottomSheetDialog(getContext());
        bottomSheetDialogForOperator.setContentView(R.layout.bottom_sheet_dialog_layout_for_operator);


        gpButton = bottomSheetDialogForOperator.findViewById(R.id.gpLayoutId);
        robiButton = bottomSheetDialogForOperator.findViewById(R.id.robiLayoutId);
        blButton = bottomSheetDialogForOperator.findViewById(R.id.BLLayoutId);
        airtelButton = bottomSheetDialogForOperator.findViewById(R.id.airLayoutId);
        teletalkButton = bottomSheetDialogForOperator.findViewById(R.id.telLayoutId);
        gpButton.setOnClickListener(this);
        robiButton.setOnClickListener(this);
        blButton.setOnClickListener(this);
        airtelButton.setOnClickListener(this);
        teletalkButton.setOnClickListener(this);


        bottomSheetDialogForOperator.show();
    }

    private void showBottomSheetDialogForMbanking() {

        viewForMBanking = new BottomSheetDialog(getContext());
        viewForMBanking.setContentView(R.layout.bottom_sheet_dialog_layout_for_mbanking);


        bkashButton = viewForMBanking.findViewById(R.id.bkashLayoutId);
        rcketButton = viewForMBanking.findViewById(R.id.rocketLayoutId);
        nagadButton = viewForMBanking.findViewById(R.id.nagadLayoutId);

        bkashButton.setOnClickListener(this);
        rcketButton.setOnClickListener(this);
        nagadButton.setOnClickListener(this);


        viewForMBanking.show();
    }


}