package com._99Recharge.customer.service.repository;


import com._99Recharge.customer.service.model.responseBody.CommonResponse;

public interface IDomainChkResponse {
    void onSuccess(CommonResponse response);
    void onFailure(Throwable t);
}
