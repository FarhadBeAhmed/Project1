package com._99Recharge.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddResellerRequest {
    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("f_name")
    @Expose
    private String f_name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("newUser_ID")
    @Expose
    private String newUser_ID;

    @SerializedName("pin")
    @Expose
    private String pin;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("optionsRadios")
    @Expose
    private String optionsRadios;


    public AddResellerRequest(String user_id, String f_name, String email, String mobile, String address, String newUser_ID, String pin, String password, String optionsRadios) {
        this.user_id = user_id;
        this.f_name = f_name;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.newUser_ID = newUser_ID;
        this.pin = pin;
        this.password = password;
        this.optionsRadios = optionsRadios;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNewUser_ID() {
        return newUser_ID;
    }

    public void setNewUser_ID(String newUser_ID) {
        this.newUser_ID = newUser_ID;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOptionsRadios() {
        return optionsRadios;
    }

    public void setOptionsRadios(String optionsRadios) {
        this.optionsRadios = optionsRadios;
    }


}
