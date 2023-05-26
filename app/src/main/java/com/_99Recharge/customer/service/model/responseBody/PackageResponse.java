package com._99Recharge.customer.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PackageResponse {
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("packData")
    @Expose
    private List<PackDatum> packData;

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

    public List<PackDatum> getPackData() {
        return packData;
    }

    public void setPackData(List<PackDatum> packData) {
        this.packData = packData;
    }
}
