package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.MobileRechargeResponse;

public interface IMobileRecharge {
     void onResponse(MobileRechargeResponse mobileRechargeResponse);
     void onFailure(Throwable t);
}
