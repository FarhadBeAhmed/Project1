package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.MobileRechargeResponse;

public interface IMobileRecharge {
     void onResponse(MobileRechargeResponse mobileRechargeResponse);
     void onFailure(Throwable t);
}
