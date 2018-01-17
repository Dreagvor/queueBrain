package com.zoxal.queuebrain.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

/**
 * Enumerates possible application errors
 *
 * @author Mike
 * @version 12/25/2017
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@JsonIgnoreProperties("responseStatus")
public enum ExceptionCode {
    COMMON_TECHNICAL("QB-T-001", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("QB-B-001", "Request validation error", HttpStatus.BAD_REQUEST),
    NOT_FOUND("QB-B-002", "Requested object not found", HttpStatus.NOT_FOUND),
    INVALID_CREDENTIALS("QB-B-003", "Invalid credentials", HttpStatus.UNAUTHORIZED),
    FORBIDDEN("QB-B-004", "Forbidden", HttpStatus.FORBIDDEN);

    @JsonProperty(value = "errorCode")
    private String code;

    @JsonProperty(value = "codeDescription")
    private String description;

    private int responseStatus;

    ExceptionCode(String code, String description, HttpStatus responseStatus) {
        this.code = code;
        this.description = description;
        this.responseStatus = responseStatus.value();
    }

    public String getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public int getResponseStatus() {
        return responseStatus;
    }
}
