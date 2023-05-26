package com.app_99recharge.service.repository;


import com.app_99recharge.service.model.responseBody.RechargeHistoryResponse;

public interface IRechargeHistoryResponse {
    void onResponse(RechargeHistoryResponse historyResponse);
    void onFailure(Throwable t);
}
