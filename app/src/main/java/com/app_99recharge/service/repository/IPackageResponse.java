package com.app_99recharge.service.repository;

import com.app_99recharge.service.model.responseBody.PackageResponse;

public interface IPackageResponse {
    void onResponse(PackageResponse packageResponse);
    void onFailure(Throwable t);

}
