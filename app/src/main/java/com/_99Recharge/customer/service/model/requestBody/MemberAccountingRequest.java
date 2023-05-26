package com._99Recharge.customer.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemberAccountingRequest {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("other_user")
    @Expose
    private String other_user;

    public MemberAccountingRequest(String user_id, String other_user) {
        this.user_id = user_id;
        this.other_user = other_user;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getOther_user() {
        return other_user;
    }

    public void setOther_user(String other_user) {
        this.other_user = other_user;
    }
}
