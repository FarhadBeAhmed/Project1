package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.DeviceIdResponse;

public interface IDeviceIdResponse {
    void onResponse(DeviceIdResponse deviceIdResponse);
    void onFailure(Throwable t);
}
