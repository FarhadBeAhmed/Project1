package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.MemAccActionResponse;

public interface IMemAccActionResponse {
    void onResponse(MemAccActionResponse memAccActionResponse);
    void onFailure(Throwable t);
}
