package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.CommonResponse;
import com.example.project1.service.model.responseBody.PinChkResponse;

public interface IPassPinnResponse {
    void onResponse(CommonResponse response);


    void onFailure(Throwable t);
}
