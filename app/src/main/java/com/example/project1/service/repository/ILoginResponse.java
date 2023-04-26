package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.LoginResponse;

public interface ILoginResponse {
    void onResponse(LoginResponse loginResponse);


    void onFailure(Throwable t);
}
