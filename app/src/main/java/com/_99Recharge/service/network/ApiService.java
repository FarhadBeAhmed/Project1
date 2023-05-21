package com._99Recharge.service.network;

import com._99Recharge.service.model.requestBody.AddResellerRequest;
import com._99Recharge.service.model.requestBody.DeviceIdRequest;
import com._99Recharge.service.model.requestBody.DomainChkRequest;
import com._99Recharge.service.model.requestBody.HomeInfoRequest;
import com._99Recharge.service.model.requestBody.LoginRequestBody;
import com._99Recharge.service.model.requestBody.MemAccActionRequest;
import com._99Recharge.service.model.requestBody.MemberAccountingRequest;
import com._99Recharge.service.model.requestBody.MobileRechargeRequest;
import com._99Recharge.service.model.requestBody.PackagesRequest;
import com._99Recharge.service.model.requestBody.PassPinRequest;
import com._99Recharge.service.model.requestBody.RechargeHistoryRequest;
import com._99Recharge.service.model.responseBody.CommonResponse;
import com._99Recharge.service.model.responseBody.DeviceIdResponse;
import com._99Recharge.service.model.responseBody.HomeInfoResponse;
import com._99Recharge.service.model.responseBody.LoginResponse;
import com._99Recharge.service.model.responseBody.MemAccActionResponse;
import com._99Recharge.service.model.responseBody.MemberAccountingResponse;
import com._99Recharge.service.model.responseBody.MobileRechargeResponse;
import com._99Recharge.service.model.responseBody.PackageResponse;
import com._99Recharge.service.model.responseBody.PinChkResponse;
import com._99Recharge.service.model.responseBody.RechargeHistoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/app/login_chk.php")
    Call<LoginResponse>login(@Body LoginRequestBody loginRequestBody);

    @POST("/app/pin_chk.php")
    Call<PinChkResponse>pinChk(@Body LoginRequestBody loginRequestBody);

    @POST("/app/home_info.php")
    Call<HomeInfoResponse>homeInfo(@Body HomeInfoRequest homeInfoRequest);

    @POST("/app/device_chk.php")
    Call<DeviceIdResponse>checkDevice(@Body DeviceIdRequest deviceIdRequest);
    @POST("/app/mobile_recharge.php")
    Call<MobileRechargeResponse>recharge(@Body MobileRechargeRequest mobileRechargeRequest);

    @POST("/app/load_bd_packages.php")
    Call<PackageResponse>load_packages(@Body PackagesRequest packagesRequest);

    @POST("/app/domain_chk.php")
    Call<CommonResponse>domainChk(@Body DomainChkRequest packagesRequest);

    @POST("/app/getRecharges.php")
    Call<RechargeHistoryResponse>getRechargeHistory(@Body RechargeHistoryRequest rechargeHistoryRequest);

    @POST("/app/change_pin_pass.php")
    Call<CommonResponse>updatePassPin(@Body PassPinRequest passPinRequest);
    @POST("/app/add_reseller.php")
    Call<CommonResponse>add_reseller(@Body AddResellerRequest addResellerRequest);

    @POST("/app/get_member_accounting.php")
    Call<MemberAccountingResponse>add_balance(@Body MemberAccountingRequest memberAccountingRequest);

    @POST("/app/member_acc_act.php")
    Call<MemAccActionResponse>acc_action(@Body MemAccActionRequest memAccActionRequest);


}
