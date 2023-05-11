package com.example.project1.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechargeHistoryResponse {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private List<RechargeHistoryData> data;

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

    public List<RechargeHistoryData> getData() {
        return data;
    }

    public void setData(List<RechargeHistoryData> data) {
        this.data = data;
    }
}
