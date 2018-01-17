package com.zoxal.queuebrain.security.dto;

/**
 * Represents successful login response. Contains session id
 * to authenticate for subsequent api calls.
 *
 * @author Mike
 * @version 01/08/2018
 */
public class SuccessLoginResponse {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
