package com._99Recharge.customer.service.FHelper;

import androidx.annotation.NonNull;

import com._99Recharge.customer.service.FHelper.Annotations.Field;
import com._99Recharge.customer.service.FHelper.Annotations.RequestUrl;
import com._99Recharge.customer.service.FHelper.Annotations.SendMethod;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;



public interface API {
    @RequestUrl("app/signUpFirstStep.php")
    @SendMethod(Request.Method.POST)
    @NonNull
    JsonObjectRequest signUpFirstStep(
            @Field(ConstantValues.Login.USERNAME) String username,
            @Field(ConstantValues.Login.PIN) int pin,
            Response.Listener<JSONObject> listener);

    @RequestUrl("app/home_info.php")
    @SendMethod(Request.Method.POST)
    @NonNull
    JsonObjectRequest home_info(
            @Field(ConstantValues.Login.USERNAME) String username,
            @Field(ConstantValues.Login.PIN) int pin,
            Response.Listener<JSONObject> listener);



}