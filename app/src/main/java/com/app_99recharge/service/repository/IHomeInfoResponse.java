package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.HomeInfoResponse;

public interface IHomeInfoResponse {
    void onResponse(HomeInfoResponse homeInfoResponse);
    void onFailure(Throwable t);
}
