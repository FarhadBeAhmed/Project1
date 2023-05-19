package com._99Recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.service.model.requestBody.MobileRechargeRequest;
import com._99Recharge.service.model.responseBody.MobileRechargeResponse;
import com._99Recharge.service.repository.IMobileRecharge;
import com._99Recharge.service.repository.MobileRechargeRepo;
import com._99Recharge.view.ui.PinActivity;

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
