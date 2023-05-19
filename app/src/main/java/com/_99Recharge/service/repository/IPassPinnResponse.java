package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.CommonResponse;

public interface IPassPinnResponse {
    void onResponse(CommonResponse response);


    void onFailure(Throwable t);
}
