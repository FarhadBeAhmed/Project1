package com._99Recharge.customer.service.repository;

import com._99Recharge.customer.service.model.responseBody.PackageResponse;

public interface IPackageResponse {
    void onResponse(PackageResponse packageResponse);
    void onFailure(Throwable t);

}
