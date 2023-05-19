package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.HomeInfoResponse;

public interface IHomeInfoResponse {
    void onResponse(HomeInfoResponse homeInfoResponse);
    void onFailure(Throwable t);
}
