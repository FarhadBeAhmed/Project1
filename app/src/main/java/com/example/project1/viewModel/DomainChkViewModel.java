package com.example.project1.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.DomainChkRequest;
import com.example.project1.service.model.responseBody.CommonResponse;
import com.example.project1.service.model.responseBody.DeviceIdResponse;
import com.example.project1.service.repository.DomainChkRepo;
import com.example.project1.service.repository.IDomainChkResponse;

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
