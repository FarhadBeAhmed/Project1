package com.example.project1.service.repository;


import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.responseBody.LoginResponse;
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
}
