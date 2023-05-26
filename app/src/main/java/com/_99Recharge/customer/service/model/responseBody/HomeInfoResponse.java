package com._99Recharge.customer.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeInfoResponse {
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("balance")
    @Expose
    private String balance;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("flag")
    @Expose
    private String flag;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
