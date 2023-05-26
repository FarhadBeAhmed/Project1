package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.PinChkResponse;

public interface IPinchk {
    void onResponse(PinChkResponse pinChkResponse);


    void onFailure(Throwable t);
}
