package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.requestBody.MemberAccountingRequest;
import com.app_99recharge.service.model.responseBody.MemberAccountingResponse;
import com.app_99recharge.service.network.ApiService;
import com.app_99recharge.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MemberAccountingRepo {

    @Inject
    ApiService apiService;

    public void callForAccounting(MemberAccountingRequest memberAccountingRequest, IMemberAccountingResponse iMemberAccountingResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<MemberAccountingResponse> initiateCall=apiService.add_balance(memberAccountingRequest);
        initiateCall.enqueue(new Callback<MemberAccountingResponse>() {
            @Override
            public void onResponse(Call<MemberAccountingResponse> call, Response<MemberAccountingResponse> response) {
                if (response.isSuccessful()){
                    iMemberAccountingResponse.onResponse(response.body());
                }else {
                    iMemberAccountingResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<MemberAccountingResponse> call, Throwable t) {
                iMemberAccountingResponse.onFailure(t);
            }
        });


    }
}
