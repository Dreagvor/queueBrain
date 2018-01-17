package com.zoxal.queuebrain.security.api;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Authentication object that contains user registration link
 *
 * @author Mike
 * @version 01/16/2018
 */
public class RegistrationLinkAuthToken extends UsernamePasswordAuthenticationToken {
    private String registrationLink;

    public RegistrationLinkAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }
}
