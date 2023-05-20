package com._99Recharge.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemAccActionResponse {
    @SerializedName("res_value")
    @Expose
    private Integer res_value;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("msg")
    @Expose
    private Integer msg;

    public Integer getRes_value() {
        return res_value;
    }

    public void setRes_value(Integer res_value) {
        this.res_value = res_value;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }
}
