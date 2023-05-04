package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.MobileRechargeResponse;

public interface IMobileRecharge {
     void onResponse(MobileRechargeResponse mobileRechargeResponse);
     void onFailure(Throwable t);
}
