package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.PinChkResponse;

public interface IPinchk {
    void onResponse(PinChkResponse pinChkResponse);


    void onFailure(Throwable t);
}
