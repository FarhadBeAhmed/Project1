package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.HomeInfoRequest;
import com.app_99recharge.service.model.responseBody.HomeInfoResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomInfoRepo {
    @Inject
    ApiService apiService;

    public void homeRemote(HomeInfoRequest homeInfoRequest, IHomeInfoResponse iHomeInfoResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<HomeInfoResponse> initiateHomeInfo=apiService.homeInfo(homeInfoRequest);

        initiateHomeInfo.enqueue(new Callback<HomeInfoResponse>() {
            @Override
            public void onResponse(Call<HomeInfoResponse> call, Response<HomeInfoResponse> response) {
                if (response.isSuccessful()){
                    iHomeInfoResponse.onResponse(response.body());
                }else {
                    iHomeInfoResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<HomeInfoResponse> call, Throwable t) {
                iHomeInfoResponse.onFailure(t);
            }
        });
    }
}
