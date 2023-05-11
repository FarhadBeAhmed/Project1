package com.example.project1.service.repository;

import com.example.project1.service.model.responseBody.LoginResponse;
import com.example.project1.service.model.responseBody.PinChkResponse;

public interface IPinchk {
    void onResponse(PinChkResponse pinChkResponse);


    void onFailure(Throwable t);
}
