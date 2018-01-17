package com.zoxal.queuebrain.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstLoginRequest {
    private String registrationLink;

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }
}
