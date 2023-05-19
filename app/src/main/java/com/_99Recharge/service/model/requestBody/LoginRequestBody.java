package com._99Recharge.service.model.requestBody;

import com.google.gson.annotations.SerializedName;

public class LoginRequestBody {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("deviceID")
    private String deviceID;

    public LoginRequestBody(String username, String password, String deviceID) {
        this.username = username;
        this.password = password;
        this.deviceID = deviceID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }
}
