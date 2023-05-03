package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.HomeInfoResponse;

public interface IHomeInfoResponse {
    void onResponse(HomeInfoResponse homeInfoResponse);
    void onFailure(Throwable t);
}
