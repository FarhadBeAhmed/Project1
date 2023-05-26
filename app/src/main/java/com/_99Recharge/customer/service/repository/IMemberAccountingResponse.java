package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.MemberAccountingResponse;

public interface IMemberAccountingResponse {
    void onResponse(MemberAccountingResponse memberAccountingResponse);
    void onFailure(Throwable t);
}
