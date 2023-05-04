package com.example.project1.service.network;

import com.example.project1.service.model.requestBody.DeviceIdRequest;
import com.example.project1.service.model.requestBody.HomeInfoRequest;
import com.example.project1.service.model.requestBody.LoginRequestBody;
import com.example.project1.service.model.requestBody.MobileRechargeRequest;
import com.example.project1.service.model.requestBody.PackagesRequest;
import com.example.project1.service.model.responseBody.DeviceIdResponse;
import com.example.project1.service.model.responseBody.HomeInfoResponse;
import com.example.project1.service.model.responseBody.LoginResponse;
import com.example.project1.service.model.responseBody.MobileRechargeResponse;
import com.example.project1.service.model.responseBody.PackageResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/app/login_chk.php")
    Call<LoginResponse>login(@Body LoginRequestBody loginRequestBody);

    @POST("/app/pin_chk.php")
    Call<LoginResponse>pinChk(@Body LoginRequestBody loginRequestBody);

    @POST("/app/home_info.php")
    Call<HomeInfoResponse>homeInfo(@Body HomeInfoRequest homeInfoRequest);

    @POST("/app/device_chk.php")
    Call<DeviceIdResponse>checkDevice(@Body DeviceIdRequest deviceIdRequest);
    @POST("/app/mobile_recharge.php")
    Call<MobileRechargeResponse>recharge(@Body MobileRechargeRequest mobileRechargeRequest);

    @POST("/app/load_bd_packages.php")
    Call<PackageResponse>load_packages(@Body PackagesRequest packagesRequest);


}
