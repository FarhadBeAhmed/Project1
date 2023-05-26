package com.app_99recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_99recharge.service.model.requestBody.DomainChkRequest;
import com.app_99recharge.service.model.responseBody.CommonResponse;
import com.app_99recharge.service.repository.DomainChkRepo;
import com.app_99recharge.service.repository.IDomainChkResponse;

public class DomainChkViewModel extends ViewModel {
    DomainChkRepo domainChkRepo;
    MutableLiveData<CommonResponse>mDomain=new MutableLiveData<>();

    public DomainChkViewModel(){
        domainChkRepo=new DomainChkRepo();
    }
    public void callForDomainChk(String domain){

        domainChkRepo.callingDomainApi(new DomainChkRequest(domain), new IDomainChkResponse() {
            @Override
            public void onSuccess(CommonResponse response) {
                mDomain.postValue(response);
            }

            @Override
            public void onFailure(Throwable t) {
                mDomain.setValue(new CommonResponse());

            }
        });

    }
    public LiveData<CommonResponse> getData(){
        return mDomain;
    }


}
