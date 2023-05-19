package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.MobileRechargeResponse;

public interface IMobileRecharge {
     void onResponse(MobileRechargeResponse mobileRechargeResponse);
     void onFailure(Throwable t);
}
