package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.MobileRechargeRequest;
import com.app_99recharge.service.model.responseBody.MobileRechargeResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileRechargeRepo {
    @Inject
    ApiService apiService;

    public void rechargeRemote(MobileRechargeRequest mobileRechargeRequest, IMobileRecharge iMobileRecharge){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<MobileRechargeResponse>initialCall=apiService.recharge(mobileRechargeRequest);
        initialCall.enqueue(new Callback<MobileRechargeResponse>() {
            @Override
            public void onResponse(Call<MobileRechargeResponse> call, Response<MobileRechargeResponse> response) {
                if (response.isSuccessful()){
                    iMobileRecharge.onResponse(response.body());
                }else{
                    iMobileRecharge.onFailure(new Throwable(response.message()));

                }
            }

            @Override
            public void onFailure(Call<MobileRechargeResponse> call, Throwable t) {
                iMobileRecharge.onFailure(t);
            }
        });
    }
}
