package com._99Recharge.service.repository;

import com._99Recharge.service.model.responseBody.PackageResponse;

public interface IPackageResponse {
    void onResponse(PackageResponse packageResponse);
    void onFailure(Throwable t);

}
