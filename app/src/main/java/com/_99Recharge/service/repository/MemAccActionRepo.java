package com._99Recharge.service.repository;

import com._99Recharge.service.model.requestBody.DeviceIdRequest;
import com._99Recharge.service.model.requestBody.MemAccActionRequest;
import com._99Recharge.service.model.responseBody.DeviceIdResponse;
import com._99Recharge.service.model.responseBody.MemAccActionResponse;
import com._99Recharge.service.model.responseBody.MemberAccountingResponse;
import com._99Recharge.service.network.ApiService;
import com._99Recharge.service.network.RetrofitClientInstance;

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
