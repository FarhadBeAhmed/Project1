package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.CommonResponse;

public interface IAddResellerResponse {
    void onResponse(CommonResponse commonResponse);
    void onFailure(Throwable t);
}
