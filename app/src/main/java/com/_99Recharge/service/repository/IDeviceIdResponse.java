package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.DeviceIdResponse;

public interface IDeviceIdResponse {
    void onResponse(DeviceIdResponse deviceIdResponse);
    void onFailure(Throwable t);
}
