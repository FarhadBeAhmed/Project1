package com.example.project1.service.network;

import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.responseBody.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("app/login_chk.php")
    Call<LoginResponse>login(@Body LoginRequestBody loginRequestBody);


}
