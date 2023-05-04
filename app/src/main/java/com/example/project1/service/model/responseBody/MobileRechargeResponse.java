package com.example.project1.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileRechargeResponse {
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("msg")
    @Expose
    private String msg;

    public  MobileRechargeResponse(String error, String msg) {
        this.error = error;
        this.msg = msg;
    }
    public  MobileRechargeResponse() {}

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
