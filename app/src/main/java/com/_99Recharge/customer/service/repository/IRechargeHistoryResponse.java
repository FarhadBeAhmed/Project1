package com._99Recharge.customer.service.repository;


import com._99Recharge.customer.service.model.responseBody.RechargeHistoryResponse;

public interface IRechargeHistoryResponse {
    void onResponse(RechargeHistoryResponse historyResponse);
    void onFailure(Throwable t);
}
