package com._99Recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com._99Recharge.service.model.requestBody.AddResellerRequest;
import com._99Recharge.service.model.responseBody.CommonResponse;
import com._99Recharge.service.repository.AddResellerRepo;
import com._99Recharge.service.repository.IAddResellerResponse;

public class AddResellerViewModel extends ViewModel {

    AddResellerRepo repo;
    MutableLiveData<CommonResponse> mAddReseller=new MutableLiveData<>();

    public AddResellerViewModel(){
        repo=new AddResellerRepo();
    }
    public void callForAddReseller(String user_id,String f_name,String l_name,String email,String mobile,String address,String newUser_ID,String password,String pin,String optionsRadios){
        repo.callForAddReseller(new AddResellerRequest(user_id,f_name,email,mobile,address,newUser_ID,password,pin,optionsRadios), new IAddResellerResponse() {
            @Override
            public void onResponse(CommonResponse deviceIdResponse) {
                mAddReseller.postValue(deviceIdResponse);
            }
            @Override
            public void onFailure(Throwable t) {
                mAddReseller.postValue(new CommonResponse());
            }
        });
    }

    public LiveData<CommonResponse> getData(){
        return mAddReseller;
    }
}
