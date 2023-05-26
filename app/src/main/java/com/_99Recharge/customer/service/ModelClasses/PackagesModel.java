package com._99Recharge.customer.service.ModelClasses;


public class PackagesModel {
    String opImgUrl;
    String amount;
    String value;

    public PackagesModel(String opImgUrl, String amount, String value) {
        this.opImgUrl = opImgUrl;
        this.amount = amount;
        this.value = value;
    }

    public String getOpImgUrl() {
        return opImgUrl;
    }

    public void setOpImgUrl(String opImgUrl) {
        this.opImgUrl = opImgUrl;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
