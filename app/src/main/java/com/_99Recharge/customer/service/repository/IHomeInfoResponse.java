package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.HomeInfoResponse;

public interface IHomeInfoResponse {
    void onResponse(HomeInfoResponse homeInfoResponse);
    void onFailure(Throwable t);
}
