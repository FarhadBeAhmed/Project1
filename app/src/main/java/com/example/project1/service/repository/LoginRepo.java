package com.example.project1.service.repository;


import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.responseBody.LoginResponse;
import com.example.project1.service.model.responseBody.PinChkResponse;
import com.example.project1.service.network.ApiService;
import com.example.project1.service.network.RetrofitClientInstance;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepo {

    @Inject
    ApiService apiService;

    public void loginRemote(LoginRequestBody loginRequestBody, ILoginResponse iLoginResponse){
         apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<LoginResponse> initiateLogin=apiService.login(loginRequestBody);
        initiateLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    iLoginResponse.onResponse(response.body());
                }else{
                    iLoginResponse.onFailure(new Throwable(response.message()));

                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                iLoginResponse.onFailure(t);
            }
        });

    }
    public void pinRemote(LoginRequestBody loginRequestBody, IPinchk iPinchk){
        apiService= RetrofitClientInstance.getRetroInstance().create(ApiService.class);
        Call<PinChkResponse> initiateLogin=apiService.pinChk(loginRequestBody);
        initiateLogin.enqueue(new Callback<PinChkResponse>() {
            @Override
            public void onResponse(Call<PinChkResponse> call, Response<PinChkResponse> response) {
                if (response.isSuccessful()){
                    iPinchk.onResponse(response.body());
                }else{
                    iPinchk.onFailure(new Throwable(response.message()));

                }
            }

            @Override
            public void onFailure(Call<PinChkResponse> call, Throwable t) {
                iPinchk.onFailure(t);
            }
        });

    }
}
