package com._99Recharge.service.repository;

import com._99Recharge.service.model.requestBody.MemberAccountingRequest;
import com._99Recharge.service.model.responseBody.MemberAccountingResponse;
import com._99Recharge.service.network.ApiService;
import com._99Recharge.service.network.RetrofitClientInstance;

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
