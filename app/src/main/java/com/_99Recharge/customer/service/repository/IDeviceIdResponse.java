package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.DeviceIdResponse;

public interface IDeviceIdResponse {
    void onResponse(DeviceIdResponse deviceIdResponse);
    void onFailure(Throwable t);
}
