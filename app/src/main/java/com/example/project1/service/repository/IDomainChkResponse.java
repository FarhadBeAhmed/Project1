package com.example.project1.service.repository;


import com.example.project1.service.model.responseBody.CommonResponse;

public interface IDomainChkResponse {
    void onSuccess(CommonResponse response);
    void onFailure(Throwable t);
}
