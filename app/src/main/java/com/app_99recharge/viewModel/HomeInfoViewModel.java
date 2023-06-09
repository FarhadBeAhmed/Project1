package com.app_99recharge.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app_99recharge.service.model.requestBody.HomeInfoRequest;
import com.app_99recharge.service.model.responseBody.HomeInfoResponse;
import com.app_99recharge.service.repository.HomInfoRepo;
import com.app_99recharge.service.repository.IHomeInfoResponse;

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
