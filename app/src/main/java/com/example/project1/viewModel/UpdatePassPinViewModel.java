package com.example.project1.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.PassPinRequest;
import com.example.project1.service.model.responseBody.CommonResponse;
import com.example.project1.service.repository.IPassPinnResponse;
import com.example.project1.service.repository.PassPinRepo;

public class UpdatePassPinViewModel extends ViewModel {

    PassPinRepo passPinRepo;
    MutableLiveData<CommonResponse>mPinPass=new MutableLiveData<>();

    public UpdatePassPinViewModel(){
        passPinRepo=new PassPinRepo();
    }

    public void updatePinPass(String old_number,String new_number,String confirm_number,String type){
        passPinRepo.updatePinPass(new PassPinRequest(old_number, new_number, confirm_number, type), new IPassPinnResponse() {
            @Override
            public void onResponse(CommonResponse response) {
                mPinPass.postValue(response);
            }

            @Override
            public void onFailure(Throwable t) {
                mPinPass.postValue(new CommonResponse());
            }
        });

    }

    public LiveData<CommonResponse> getData(){
        return mPinPass;
    }


}
