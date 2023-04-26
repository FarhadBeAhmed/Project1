package com.example.project1.FHelper;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.project1.FHelper.Annotations.Field;
import com.example.project1.FHelper.Annotations.RequestUrl;
import com.example.project1.FHelper.Annotations.SendMethod;

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