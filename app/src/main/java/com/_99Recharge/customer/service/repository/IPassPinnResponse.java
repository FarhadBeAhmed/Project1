package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.CommonResponse;

public interface IPassPinnResponse {
    void onResponse(CommonResponse response);


    void onFailure(Throwable t);
}
