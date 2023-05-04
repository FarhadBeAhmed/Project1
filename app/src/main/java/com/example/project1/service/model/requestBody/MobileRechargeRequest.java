package com.example.project1.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MobileRechargeRequest {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("nmp")
    @Expose
    private String nmp;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("otc")
    @Expose
    private String otc;
    @SerializedName("pin")
    @Expose
    private String pin;

    public MobileRechargeRequest(String user_id, String type, String nmp, String mobile, String amount, String otc, String pin) {
        this.user_id = user_id;
        this.type = type;
        this.nmp = nmp;
        this.mobile = mobile;
        this.amount = amount;
        this.otc = otc;
        this.pin = pin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNmp() {
        return nmp;
    }

    public void setNmp(String nmp) {
        this.nmp = nmp;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOtc() {
        return otc;
    }

    public void setOtc(String otc) {
        this.otc = otc;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}

