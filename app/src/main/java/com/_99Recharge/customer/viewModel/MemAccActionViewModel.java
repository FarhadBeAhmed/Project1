package com._99Recharge.customer.viewModel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com._99Recharge.customer.service.model.requestBody.MemAccActionRequest;
import com._99Recharge.customer.service.model.responseBody.MemAccActionResponse;
import com._99Recharge.customer.service.repository.IMemAccActionResponse;
import com._99Recharge.customer.service.repository.MemAccActionRepo;
import com._99Recharge.customer.view.ui.PinActivity;

public class MemAccActionViewModel extends ViewModel {

    MemAccActionRepo memAccActionRepo;
    MutableLiveData<MemAccActionResponse> mutableLiveData=new MutableLiveData<>();

    public MemAccActionViewModel(){
        memAccActionRepo=new MemAccActionRepo();
    }
    public void callForMemAcc(String user_id,String serial,String amount,String remark,String pin,String tran){
        memAccActionRepo.callForAccAction(new MemAccActionRequest(user_id,serial,amount,remark,pin,tran, PinActivity.pinNumber.getTemp_pin()), new IMemAccActionResponse() {
            @Override
            public void onResponse(MemAccActionResponse memberAccountingResponse) {
                mutableLiveData.postValue(memberAccountingResponse);
            }
            @Override
            public void onFailure(Throwable t) {
                mutableLiveData.postValue(new MemAccActionResponse());
            }
        });
    }

    public LiveData<MemAccActionResponse> getLiveData(){
        return mutableLiveData;
    }
}
