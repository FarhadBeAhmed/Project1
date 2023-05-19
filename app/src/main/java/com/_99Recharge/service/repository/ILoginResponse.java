package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.LoginResponse;

public interface ILoginResponse {
    void onResponse(LoginResponse loginResponse);


    void onFailure(Throwable t);
}
