package com.zoxal.queuebrain.model.dto;

public class UpdateUserRequest {
    private String contacts;
    private String password;

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
