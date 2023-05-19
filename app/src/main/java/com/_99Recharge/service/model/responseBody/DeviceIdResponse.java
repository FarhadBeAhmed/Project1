package com._99Recharge.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceIdResponse {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
