package com.app_99recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_99recharge.service.model.requestBody.MobileRechargeRequest;
import com.app_99recharge.service.model.responseBody.MobileRechargeResponse;
import com.app_99recharge.service.repository.IMobileRecharge;
import com.app_99recharge.service.repository.MobileRechargeRepo;
import com.app_99recharge.view.ui.PinActivity;

public class MobileRechargeViewModel extends ViewModel {
    MutableLiveData<MobileRechargeResponse>mRecharge=new MutableLiveData<>();
    MobileRechargeRepo mobileRechargeRepo;

    public MobileRechargeViewModel() {
        mobileRechargeRepo = new MobileRechargeRepo();
    }

    public void recharge(String user_id,String number,String amount ,String type,String nmp){
        mobileRechargeRepo.rechargeRemote(new MobileRechargeRequest(user_id, type, nmp, number, amount, PinActivity.pinNumber.getTemp_pin()), new IMobileRecharge() {
            @Override
            public void onResponse(MobileRechargeResponse mobileRechargeResponse) {
                mRecharge.postValue(mobileRechargeResponse);
            }

            @Override
            public void onFailure(Throwable t) {
                mRecharge.postValue(new MobileRechargeResponse());
            }
        });

    }

    public LiveData<MobileRechargeResponse>getResponse(){return mRecharge;}

}
