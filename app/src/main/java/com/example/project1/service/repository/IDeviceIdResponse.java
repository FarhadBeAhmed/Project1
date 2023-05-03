package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.DeviceIdResponse;

public interface IDeviceIdResponse {
    void onResponse(DeviceIdResponse deviceIdResponse);
    void onFailure(Throwable t);
}
