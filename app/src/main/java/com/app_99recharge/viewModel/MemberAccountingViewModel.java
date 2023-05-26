package com.app_99recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_99recharge.service.model.requestBody.MemberAccountingRequest;
import com.app_99recharge.service.model.responseBody.MemberAccountingResponse;
import com.app_99recharge.service.repository.IMemberAccountingResponse;
import com.app_99recharge.service.repository.MemberAccountingRepo;

public class MemberAccountingViewModel extends ViewModel {

    MemberAccountingRepo memberAccountingRepo;
    MutableLiveData<MemberAccountingResponse> mutableLiveData=new MutableLiveData<>();

    public MemberAccountingViewModel(){
        memberAccountingRepo=new MemberAccountingRepo();
    }
    public void callForMemAcc(String user_id,String other_user){
        memberAccountingRepo.callForAccounting(new MemberAccountingRequest(user_id,other_user), new IMemberAccountingResponse() {
            @Override
            public void onResponse(MemberAccountingResponse memberAccountingResponse) {
                mutableLiveData.postValue(memberAccountingResponse);
            }
            @Override
            public void onFailure(Throwable t) {
                mutableLiveData.postValue(new MemberAccountingResponse());
            }
        });
    }

    public LiveData<MemberAccountingResponse> getLiveData(){
        return mutableLiveData;
    }


}
