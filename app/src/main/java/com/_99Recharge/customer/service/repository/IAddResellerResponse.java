package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.CommonResponse;

public interface IAddResellerResponse {
    void onResponse(CommonResponse commonResponse);
    void onFailure(Throwable t);
}
