package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.CommonResponse;

public interface IAddResellerResponse {
    void onResponse(CommonResponse commonResponse);
    void onFailure(Throwable t);
}
