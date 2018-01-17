package com.zoxal.queuebrain.security.dto;

/**
 * Client request to login via login and password.
 *
 * @author Mike
 * @version 01/08/2018
 */
public class LoginPassLoginRequest {
    private String email;
    private String password;
    private String name;

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
}
