package com.app_99recharge.service.model.requestBody;

import com.google.gson.annotations.SerializedName;

public class HomeInfoRequest {
    @SerializedName("username")
    private String username;
    @SerializedName("pin")
    private String pin;

    public HomeInfoRequest(String username, String pin) {
        this.username = username;
        this.pin = pin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
