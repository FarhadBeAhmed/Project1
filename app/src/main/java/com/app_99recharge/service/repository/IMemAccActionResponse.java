package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.MemAccActionResponse;

public interface IMemAccActionResponse {
    void onResponse(MemAccActionResponse memAccActionResponse);
    void onFailure(Throwable t);
}
