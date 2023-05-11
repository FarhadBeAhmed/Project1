package com.example.project1.service.repository;

import com.example.project1.service.model.requestBody.DomainChkRequest;
import com.example.project1.service.model.responseBody.CommonResponse;
import com.example.project1.service.network.ApiService;
import com.example.project1.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DomainChkRepo {
    @Inject
    ApiService apiService;
    public void callingDomainApi(DomainChkRequest domainChkRequest,IDomainChkResponse iDomainChkResponse){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<CommonResponse>initialCall= apiService.domainChk(domainChkRequest);
        initialCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful()){
                    iDomainChkResponse.onSuccess(response.body());
                }else {
                    iDomainChkResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                iDomainChkResponse.onFailure(t);

            }
        });
    }
}
