package com.zoxal.queuebrain.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.slf4j.helpers.MessageFormatter;

import java.util.UUID;

/**
 * Generic application exception
 *
 * @author Mike
 * @version 12/25/2017
 */
@JsonIgnoreProperties({"cause", "initCause", "localizedMessage", "stackTrace", "message", "suppressed"})
public class QueueBrainException extends RuntimeException {
    private ExceptionCode code;
    private UUID uuid = UUID.randomUUID();
    private String clientMessage;

    public QueueBrainException() {
        super();
    }

    public QueueBrainException(String message) {
        super(message);
    }

    public QueueBrainException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueueBrainException(Throwable cause) {
        super(cause);
    }

    public void setCode(ExceptionCode code) {
        this.code = code;
    }

    public void setClientMessage(String clientMessage, Object ... args) {
        this.clientMessage = MessageFormatter.arrayFormat(clientMessage, args).getMessage();
    }

    @JsonUnwrapped
    public ExceptionCode getCode() {
        return code;
    }

    @JsonProperty(value = "errorMessage")
    public String getClientMessage() {
        return clientMessage;
    }

    @JsonProperty(value = "errorUUID")
    public UUID getUuid() {
        return uuid;
    }
}
