package com.zoxal.queuebrain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {
    @JsonProperty(required = true)
    private String name;
    @JsonProperty(required = true)
    private String email;
    @JsonProperty(required = true)
    private String password;
    private String contacts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}
