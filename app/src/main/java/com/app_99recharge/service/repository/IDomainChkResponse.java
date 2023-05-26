package com.app_99recharge.service.repository;


import com.app_99recharge.service.model.responseBody.CommonResponse;

public interface IDomainChkResponse {
    void onSuccess(CommonResponse response);
    void onFailure(Throwable t);
}
