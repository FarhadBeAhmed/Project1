package com._99Recharge.service.repository;

import com._99Recharge.service.model.requestBody.DeviceIdRequest;
import com._99Recharge.service.model.responseBody.DeviceIdResponse;
import com._99Recharge.service.network.ApiService;
import com._99Recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceIdRepo {

    @Inject
    ApiService apiService;

    public void callForDeviceId(DeviceIdRequest deviceIdRequest, IDeviceIdResponse iDeviceIdResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<DeviceIdResponse>initiateCall=apiService.checkDevice(deviceIdRequest);
        initiateCall.enqueue(new Callback<DeviceIdResponse>() {
            @Override
            public void onResponse(Call<DeviceIdResponse> call, Response<DeviceIdResponse> response) {
                if (response.isSuccessful()){
                    iDeviceIdResponse.onResponse(response.body());
                }else {
                    iDeviceIdResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<DeviceIdResponse> call, Throwable t) {
                iDeviceIdResponse.onFailure(t);
            }
        });


    }
}
