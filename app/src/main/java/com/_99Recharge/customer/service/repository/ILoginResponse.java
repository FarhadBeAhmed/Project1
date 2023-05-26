package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.LoginResponse;

public interface ILoginResponse {
    void onResponse(LoginResponse loginResponse);


    void onFailure(Throwable t);
}
