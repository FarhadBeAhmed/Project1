package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.PinChkResponse;

public interface IPinchk {
    void onResponse(PinChkResponse pinChkResponse);


    void onFailure(Throwable t);
}
