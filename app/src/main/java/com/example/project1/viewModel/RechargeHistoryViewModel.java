package com.example.project1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.RechargeHistoryRequest;
import com.example.project1.service.model.responseBody.PackageResponse;
import com.example.project1.service.model.responseBody.RechargeHistoryResponse;
import com.example.project1.service.repository.IRechargeHistoryResponse;
import com.example.project1.service.repository.RechargeHistoryRepo;

public class RechargeHistoryViewModel extends ViewModel {
    RechargeHistoryRepo historyRepo;
    MutableLiveData<RechargeHistoryResponse>mHistory=new MutableLiveData<>();
    public RechargeHistoryViewModel(){
        historyRepo=new RechargeHistoryRepo();
    }
    public void getRecharges(String user_id){

        historyRepo.getRecharges(new RechargeHistoryRequest(user_id), new IRechargeHistoryResponse() {
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
