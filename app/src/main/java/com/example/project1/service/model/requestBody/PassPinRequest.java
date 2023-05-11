package com.example.project1.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassPinRequest {

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

    public PassPinRequest(String old_number, String new_number, String confirm_new_number, String type) {
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
}
