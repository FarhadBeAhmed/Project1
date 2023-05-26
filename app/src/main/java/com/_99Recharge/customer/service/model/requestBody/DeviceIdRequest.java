package com._99Recharge.customer.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceIdRequest {
    @SerializedName("deviceID")
    @Expose
    private String deviceID;

    public DeviceIdRequest(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
