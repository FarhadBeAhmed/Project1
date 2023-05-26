package com._99Recharge.customer.view.ui.allFragmnts;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com._99Recharge.R;
import com._99Recharge.databinding.FragmentDashboardBinding;
import com._99Recharge.customer.service.model.responseBody.HomeInfoResponse;
import com._99Recharge.customer.util.CommonTask;
import com._99Recharge.customer.util.ConstantValues;
import com._99Recharge.customer.view.ui.Add_Balance.AddBalanceActivity;
import com._99Recharge.customer.view.ui.LoginActivity;
import com._99Recharge.customer.view.ui.MBanking.Bkash.BkashActivity;
import com._99Recharge.customer.view.ui.MBanking.Nagad.NagadActivity;
import com._99Recharge.customer.view.ui.MBanking.Rocket.RocketActivity;
import com._99Recharge.customer.view.ui.MobileRecharge.FlexiloadActivity;
import com._99Recharge.customer.view.ui.PackagesFragment.AllPackagesActivity;
import com._99Recharge.customer.view.ui.PinActivity;
import com._99Recharge.customer.view.ui.ResellerActivity;
import com._99Recharge.customer.view.ui.changePassPinActivity;
import com._99Recharge.customer.viewModel.HomeInfoViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kaopiz.kprogresshud.KProgressHUD;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class DashboardFragment extends Fragment implements View.OnClickListener {


    BottomSheetDialog bottomSheetDialogForOperator, viewForMBanking;

    LinearLayout gpButton;
    LinearLayout robiButton;
    LinearLayout blButton;
    LinearLayout airtelButton;
    LinearLayout teletalkButton;

    RelativeLayout bkashButton;
    RelativeLayout rcketButton;
    RelativeLayout nagadButton;

    FragmentDashboardBinding binding;
    KProgressHUD kProgressHUD;
    HomeInfoViewModel homeInfoViewModel;
    String balance="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater,container,false);


        binding.flaxiLayoutId.setOnClickListener(this);
        binding.blLayoutId.setOnClickListener(this);
        binding.resellerLayoutId.setOnClickListener(this);

        binding.addBalLayoutId.setOnClickListener(this);
        binding.billLayoutId.setOnClickListener(this);
        binding.callingLayoutId.setOnClickListener(this);

        binding.marqueeTextId.setSelected(true);
        homeInfoViewModel=  new ViewModelProvider(this).get(HomeInfoViewModel.class);
        if (!CommonTask.getPreferences(getContext(), ConstantValues.user.USER_ID).equals("")&& PinActivity.pinNumber.getPin()!=null){

            controlProgressBar(true);
            homeInfoViewModel.homeInfo(CommonTask.getPreferences(getContext(), ConstantValues.user.USER_ID),PinActivity.pinNumber.getPin());
        }else if (!LoginActivity.userId.equals("") && PinActivity.pinNumber.getPin()!=null){
            controlProgressBar(true);
            homeInfoViewModel.homeInfo(LoginActivity.userId,PinActivity.pinNumber.getPin());
        }
       binding.reloadId.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                controlProgressBar(true);
                homeInfoViewModel.homeInfo(CommonTask.getPreferences(getContext(), ConstantValues.user.USER_ID), PinActivity.pinNumber.getPin());
                init();
            }
        });

    if (CommonTask.getPreferences(getContext(),ConstantValues.Flexiload.LEVEL).equals("1")){
        binding.resellerLayoutId.setVisibility(View.GONE);
    }

        init();


        return binding.getRoot();
    }


    @SuppressLint("SetTextI18n")
    private void init() {
        homeInfoViewModel.getHomeInfo().observe(getViewLifecycleOwner(), new Observer<HomeInfoResponse>() {
            @Override
            public void onChanged(HomeInfoResponse homeInfoResponse) {
                if (homeInfoResponse!=null){
                    if (Integer.parseInt(homeInfoResponse.getLevel())==1){
                        binding.resellerLayoutId.setVisibility(View.GONE);
                    }
                    balance=homeInfoResponse.getBalance();
                    CommonTask.savePreferences(getContext(),ConstantValues.Flexiload.BALANCE,homeInfoResponse.getBalance());
                    CommonTask.savePreferences(getContext(),ConstantValues.Flexiload.LEVEL,homeInfoResponse.getLevel());
                    controlProgressBar(false);
                    binding.reloadId.setRefreshing(false);
                    binding.IDProfileName.setText(homeInfoResponse.getUserId()+" (Level "+homeInfoResponse.getLevel()+")");
                    binding.netBalId.setText(homeInfoResponse.getBalance());
                    binding.dashboardProfileName.setText("SMS ID: "+homeInfoResponse.getMobile());

                }
                }


        });



    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.flaxiLayoutId:
                intent=new Intent(getContext(), FlexiloadActivity.class);
                //intent.putExtra(ConstantValues.Flexiload.BALANCE,balance);
                startActivity(intent);
                break;
            case R.id.addBalLayoutId:

                startActivity(new Intent(getContext(), AddBalanceActivity.class));
                break;
            case R.id.blLayoutId:
                showBottomSheetDialog();
                break;
            case R.id.resellerLayoutId:
                startActivity(new Intent(getContext(), ResellerActivity.class));
                break;


            case R.id.billLayoutId:
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Warning")
                        .setContentText("Process under development")
                        .setConfirmText(" OK ")
                        .show();
               // startActivity(new Intent(getContext(), BillPaymentActivity.class));
                break;
            case R.id.callingLayoutId:
                startActivity(new Intent(getContext(), changePassPinActivity.class));
                break;
            case R.id.gpLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "Grameen");
                //intent= new Intent(getContext(), GPPackageActivity.class);
                startActivity(intent);
                break;
            case R.id.robiLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "Robi");
                startActivity(intent);
                break;
            case R.id.BLLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "Banglalink");
                startActivity(intent);
                break;
            case R.id.airLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "Airtel");
                startActivity(intent);
                break;
            case R.id.telLayoutId:
                bottomSheetDialogForOperator.cancel();
                intent = new Intent(getContext(), AllPackagesActivity.class);
                intent.putExtra("data", "Teletalk");
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

    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD= KProgressHUD.create(requireContext())
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } catch (Exception e) {
            }
        } else if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
            this.kProgressHUD.dismiss();
        }
    }


}