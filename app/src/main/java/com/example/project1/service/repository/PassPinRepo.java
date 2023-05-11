package com.example.project1.service.repository;

import com.example.project1.service.model.requestBody.PassPinRequest;
import com.example.project1.service.model.responseBody.CommonResponse;
import com.example.project1.service.model.responseBody.DeviceIdResponse;
import com.example.project1.service.network.ApiService;
import com.example.project1.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassPinRepo {

    @Inject
    ApiService apiService;

    public void updatePinPass(PassPinRequest passPinRequest, IPassPinnResponse iPassPinnResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<CommonResponse> initiateCall=apiService.updatePassPin(passPinRequest);
        initiateCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()){
                    iPassPinnResponse.onResponse(response.body());
                }else {
                    iPassPinnResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                iPassPinnResponse.onFailure(t);
            }
        });


    }
    
}
