package com.app_99recharge.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemAccActionRequest {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("receiver")
    @Expose
    private String receiver;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("tran")
    @Expose
    private String tran;
    @SerializedName("temp_pin")
    @Expose
    private String temp_pin;

    public MemAccActionRequest(String user_id, String receiver, String amount, String remark, String pin, String tran, String temp_pin) {
        this.user_id = user_id;
        this.receiver = receiver;
        this.amount = amount;
        this.remark = remark;
        this.pin = pin;
        this.tran = tran;
        this.temp_pin = temp_pin;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTran() {
        return tran;
    }

    public void setTran(String tran) {
        this.tran = tran;
    }

    public String getTemp_pin() {
        return temp_pin;
    }

    public void setTemp_pin(String temp_pin) {
        this.temp_pin = temp_pin;
    }
}
