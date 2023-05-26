package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.LoginResponse;

public interface ILoginResponse {
    void onResponse(LoginResponse loginResponse);


    void onFailure(Throwable t);
}
