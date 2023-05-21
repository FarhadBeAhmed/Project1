package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.LoginResponse;
import com._99Recharge.service.model.responseBody.MemAccActionResponse;

public interface IMemAccActionResponse {
    void onResponse(MemAccActionResponse memAccActionResponse);
    void onFailure(Throwable t);
}
