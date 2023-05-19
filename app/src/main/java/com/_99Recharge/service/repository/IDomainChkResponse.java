package com._99Recharge.service.repository;


import com._99Recharge.service.model.responseBody.CommonResponse;

public interface IDomainChkResponse {
    void onSuccess(CommonResponse response);
    void onFailure(Throwable t);
}
