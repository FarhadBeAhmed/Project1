package com.example.project1.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.project1.service.model.requestBody.HomeInfoRequest;
import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.responseBody.HomeInfoResponse;
import com.example.project1.service.model.responseBody.LoginResponse;
import com.example.project1.service.repository.HomInfoRepo;
import com.example.project1.service.repository.IHomeInfoResponse;
import com.example.project1.service.repository.ILoginResponse;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeInfoViewModel extends ViewModel {
    HomInfoRepo homInfoRepo;
    HomeInfoResponse homeInformation=new HomeInfoResponse();
    MutableLiveData<HomeInfoResponse>mHomeInfoLiveData=new MutableLiveData<>();

    public HomeInfoViewModel() {
        homInfoRepo=new HomInfoRepo();
    }

    public void homeInfo(String username,String pin){
        homInfoRepo.homeRemote(new HomeInfoRequest(username,pin), new IHomeInfoResponse() {
            @Override
            public void onResponse(HomeInfoResponse homeInfoResponse) {
                if (homeInfoResponse.getError()==0) {
                    mHomeInfoLiveData.postValue(homeInfoResponse);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mHomeInfoLiveData.postValue(homeInformation);

            }
        });
    }
    public LiveData<HomeInfoResponse> getHomeInfo(){
        return mHomeInfoLiveData;
    }
}
