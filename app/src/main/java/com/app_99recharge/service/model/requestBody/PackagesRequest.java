package com.app_99recharge.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackagesRequest {
    @SerializedName("operator")
    @Expose
    private String operator;

    public PackagesRequest(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
