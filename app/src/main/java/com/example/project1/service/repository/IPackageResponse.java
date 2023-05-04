package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.PackageResponse;

import java.util.ArrayList;

public interface IPackageResponse {
    void onResponse(PackageResponse packageResponse);
    void onFailure(Throwable t);

}
