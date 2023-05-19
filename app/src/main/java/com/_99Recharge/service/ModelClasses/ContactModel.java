package com._99Recharge.service.ModelClasses;

public class ContactModel {
    String name,Number;

    public ContactModel(String name, String number) {
        this.name = name;
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
