package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.RechargeHistoryRequest;
import com.app_99recharge.service.model.responseBody.RechargeHistoryResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RechargeHistoryRepo {

    @Inject
    ApiService apiService;

    public void getRecharges(RechargeHistoryRequest rechargeHistoryRequest, IRechargeHistoryResponse iRechargeHistoryResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<RechargeHistoryResponse>initialCall=apiService.getRechargeHistory(rechargeHistoryRequest);
        initialCall.enqueue(new Callback<RechargeHistoryResponse>() {
            @Override
            public void onResponse(Call<RechargeHistoryResponse> call, Response<RechargeHistoryResponse> response) {
                if (response.isSuccessful()){
                    iRechargeHistoryResponse.onResponse(response.body());
                }else {
                    iRechargeHistoryResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<RechargeHistoryResponse> call, Throwable t) {
                iRechargeHistoryResponse.onFailure(t);
            }
        });
    }
}
