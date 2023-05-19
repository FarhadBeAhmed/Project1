package com._99Recharge.service.repository;


import com._99Recharge.service.model.responseBody.RechargeHistoryResponse;

public interface IRechargeHistoryResponse {
    void onResponse(RechargeHistoryResponse historyResponse);
    void onFailure(Throwable t);
}
