package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.MemberAccountingResponse;

public interface IMemberAccountingResponse {
    void onResponse(MemberAccountingResponse memberAccountingResponse);
    void onFailure(Throwable t);
}
