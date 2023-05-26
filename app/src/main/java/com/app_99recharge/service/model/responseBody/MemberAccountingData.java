package com.app_99recharge.service.model.responseBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberAccountingData {
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("net_bal")
    @Expose
    private String netBal;
    @SerializedName("user")
    @Expose
    private String user;

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNetBal() {
        return netBal;
    }

    public void setNetBal(String netBal) {
        this.netBal = netBal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
