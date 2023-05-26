package com._99Recharge.customer.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassPinRequest {


    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("old_number")
    @Expose
    private String old_number;
    @SerializedName("new_number")
    @Expose
    private String new_number;
    @SerializedName("confirm_new_number")
    @Expose
    private String confirm_new_number;
    @SerializedName("type")
    @Expose
    private String type;

    public PassPinRequest(String user_id, String old_number, String new_number, String confirm_new_number, String type) {
        this.user_id = user_id;
        this.old_number = old_number;
        this.new_number = new_number;
        this.confirm_new_number = confirm_new_number;
        this.type = type;
    }

    public String getOld_number() {
        return old_number;
    }

    public void setOld_number(String old_number) {
        this.old_number = old_number;
    }

    public String getNew_number() {
        return new_number;
    }

    public void setNew_number(String new_number) {
        this.new_number = new_number;
    }

    public String getConfirm_new_number() {
        return confirm_new_number;
    }

    public void setConfirm_new_number(String confirm_new_number) {
        this.confirm_new_number = confirm_new_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
