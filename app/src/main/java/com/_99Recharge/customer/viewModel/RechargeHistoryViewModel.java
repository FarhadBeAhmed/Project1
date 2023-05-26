package com._99Recharge.customer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.customer.service.model.requestBody.RechargeHistoryRequest;
import com._99Recharge.customer.service.model.responseBody.RechargeHistoryResponse;
import com._99Recharge.customer.service.repository.IRechargeHistoryResponse;
import com._99Recharge.customer.service.repository.RechargeHistoryRepo;

public class RechargeHistoryViewModel extends ViewModel {
    RechargeHistoryRepo historyRepo;
    MutableLiveData<RechargeHistoryResponse>mHistory=new MutableLiveData<>();
    public RechargeHistoryViewModel(){
        historyRepo=new RechargeHistoryRepo();
    }
    public void getRecharges(String user_id,int limit){

        historyRepo.getRecharges(new RechargeHistoryRequest(user_id,limit), new IRechargeHistoryResponse() {
            @Override
            public void onResponse(RechargeHistoryResponse historyResponse) {
                mHistory.postValue(historyResponse);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

    }
    public LiveData<RechargeHistoryResponse> getData(){
        return mHistory;
    }

}
