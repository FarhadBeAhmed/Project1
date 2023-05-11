package com.example.project1.service.model.requestBody;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DomainChkRequest {
    @SerializedName("domain")
    @Expose
    private String domain;

    public DomainChkRequest(String domain) {
        this.domain = domain;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
