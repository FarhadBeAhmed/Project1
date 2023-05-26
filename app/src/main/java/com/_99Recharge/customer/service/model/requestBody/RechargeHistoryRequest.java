package com._99Recharge.customer.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RechargeHistoryRequest {
    @SerializedName("user_id")
    @Expose
    private String msg;
    @SerializedName("limit")
    @Expose
    private int limit;

    public RechargeHistoryRequest(String msg, int limit) {
        this.msg = msg;
        this.limit = limit;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
