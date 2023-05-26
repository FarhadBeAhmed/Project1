package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.MemberAccountingResponse;

public interface IMemberAccountingResponse {
    void onResponse(MemberAccountingResponse memberAccountingResponse);
    void onFailure(Throwable t);
}
