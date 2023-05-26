package com.app_99recharge.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PinChkResponse {
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("temp_pin")
    @Expose
    private String temp_pin;

    public PinChkResponse(Integer error, String msg, String temp_pin) {
        this.error = error;
        this.msg = msg;
        this.temp_pin = temp_pin;
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

    public String getTemp_pin() {
        return temp_pin;
    }

    public void setTemp_pin(String temp_pin) {
        this.temp_pin = temp_pin;
    }
}
