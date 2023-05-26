package com.app_99recharge.view.ui.Add_Balance;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app_99recharge.databinding.ActivityAddBalanceBinding;
import com.app_99recharge.service.model.responseBody.MemberAccountingData;
import com.app_99recharge.service.model.responseBody.MemberAccountingResponse;
import com.app_99recharge.util.CommonTask;
import com.app_99recharge.util.ConstantValues;
import com.app_99recharge.view.adaptars.MemberAccountingAdapter;
import com.app_99recharge.view.ui.LoginActivity;
import com.app_99recharge.view.ui.PinActivity;
import com.app_99recharge.viewModel.MemberAccountingViewModel;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;


public class AddBalanceActivity extends AppCompatActivity {
    ActivityAddBalanceBinding binding;
    MemberAccountingViewModel model;
    KProgressHUD kProgressHUD;
    MemberAccountingAdapter adapter;
    ArrayList<MemberAccountingData>arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddBalanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model=new ViewModelProvider(this).get(MemberAccountingViewModel.class);
        binding.noRecFoundId.setVisibility(View.GONE);

        initial();


    }

    private void initial() {
        arrayList=new ArrayList<>();
        binding.recycleViewId.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MemberAccountingAdapter(this,arrayList);
        binding.recycleViewId.setAdapter(adapter);

        model.getLiveData().observe(this, new Observer<MemberAccountingResponse>() {
            @Override
            public void onChanged(MemberAccountingResponse memberAccountingResponse) {
                controlProgressBar(false);
                if (memberAccountingResponse.getError()==0){
                    ArrayList<MemberAccountingData>accList=new ArrayList<>();

                    accList.addAll(memberAccountingResponse.getData());
                    arrayList.clear();
                    arrayList.addAll(accList);
                    if (!arrayList.isEmpty()){
                        binding.recycleViewId.setVisibility(View.VISIBLE);
                        binding.noRecFoundId.setVisibility(View.GONE);
                        adapter.notifyDataSetChanged();
                    }else {
                        binding.recycleViewId.setVisibility(View.GONE);
                        binding.noRecFoundId.setVisibility(View.VISIBLE);
                    }
                }
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!CommonTask.getPreferences(getApplicationContext(), ConstantValues.user.USER_ID).equals("")&& PinActivity.pinNumber.getPin()!=null){

            controlProgressBar(true);
            model.callForMemAcc(CommonTask.getPreferences(getApplicationContext(), ConstantValues.user.USER_ID),"");
        }else if (!LoginActivity.userId.equals("") && PinActivity.pinNumber.getPin()!=null){
            controlProgressBar(true);
            model.callForMemAcc(LoginActivity.userId,"");
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    public void controlProgressBar(boolean isShowProgressBar) {
        if (isShowProgressBar) {
            try {
                if (this.kProgressHUD != null && this.kProgressHUD.isShowing()) {
                    this.kProgressHUD.dismiss();
                }
                kProgressHUD= KProgressHUD.create(this)
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