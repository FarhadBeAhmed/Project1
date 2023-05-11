package com.example.project1.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeHistoryRequest {
    @SerializedName("user_id")
    @Expose
    private String msg;

    public RechargeHistoryRequest(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
