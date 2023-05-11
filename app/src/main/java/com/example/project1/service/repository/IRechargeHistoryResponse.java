package com.example.project1.service.repository;


import com.example.project1.service.model.responseBody.RechargeHistoryResponse;

public interface IRechargeHistoryResponse {
    void onResponse(RechargeHistoryResponse historyResponse);
    void onFailure(Throwable t);
}
