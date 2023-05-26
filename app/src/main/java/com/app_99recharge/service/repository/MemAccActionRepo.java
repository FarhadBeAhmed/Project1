package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.MemAccActionRequest;
import com.app_99recharge.service.model.responseBody.MemAccActionResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemAccActionRepo {

    @Inject
    ApiService apiService;

    public void callForAccAction(MemAccActionRequest accActionRequest, IMemAccActionResponse iMemAccActionResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<MemAccActionResponse> initiateCall=apiService.acc_action(accActionRequest);
        initiateCall.enqueue(new Callback<MemAccActionResponse>() {
            @Override
            public void onResponse(Call<MemAccActionResponse> call, Response<MemAccActionResponse> response) {
                if (response.isSuccessful()){
                    iMemAccActionResponse.onResponse(response.body());
                }else {
                    iMemAccActionResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<MemAccActionResponse> call, Throwable t) {
                iMemAccActionResponse.onFailure(t);
            }
        });


    }
}
