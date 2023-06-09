package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.AddResellerRequest;
import com.app_99recharge.service.model.responseBody.CommonResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddResellerRepo {
    @Inject
    ApiService apiService;

    public void callForAddReseller(AddResellerRequest addResellerRequest, IAddResellerResponse iAddResellerResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<CommonResponse> initiateCall=apiService.add_reseller(addResellerRequest);
        initiateCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()){
                    iAddResellerResponse.onResponse(response.body());
                }else {
                    iAddResellerResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                iAddResellerResponse.onFailure(t);
            }
        });


    }
}
