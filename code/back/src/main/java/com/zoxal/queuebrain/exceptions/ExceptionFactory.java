package com.zoxal.queuebrain.exceptions;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 01/13/2018
 */
public class ExceptionFactory {
    public static QueueBrainException noSuchUserException(long userId) {
        return somethingNotFound("User", userId);
    }

    public static QueueBrainException noSuchQueueException(long queueId) {
        return somethingNotFound("Queue", queueId);
    }

    public static QueueBrainException invalidClientParameter(String parameter, String value) {
        QueueBrainException exception = new QueueBrainException("Invalid parameter");
        exception.setCode(ExceptionCode.INVALID_REQUEST);
        exception.setClientMessage("Value '{}' of parameter '{}' is invalid.", value, parameter);
        return exception;
    }

    public static QueueBrainException invalidClientParameter(String message) {
        QueueBrainException exception = new QueueBrainException(message);
        exception.setCode(ExceptionCode.INVALID_REQUEST);
        exception.setClientMessage(message);
        return exception;
    }

    public static QueueBrainException invalidCredentials() {
        QueueBrainException exception = new QueueBrainException("Client sent invalid credentials");
        exception.setCode(ExceptionCode.INVALID_CREDENTIALS);
        exception.setClientMessage("Invalid credentials.");
        return exception;
    }

    public static QueueBrainException notActivated() {
        QueueBrainException exception = new QueueBrainException("User is not activated yes");
        exception.setCode(ExceptionCode.INVALID_CREDENTIALS);
        exception.setClientMessage("User is not activated yes");
        return exception;
    }

    public static QueueBrainException forbidden() {
        QueueBrainException exception = new QueueBrainException("Forbidden access");
        exception.setCode(ExceptionCode.INVALID_CREDENTIALS);
        exception.setClientMessage("Forbidden access.");
        return exception;
    }

    public static QueueBrainException parameterMissed(String parameterName) {
        QueueBrainException exception = new QueueBrainException("Missed required param");
        exception.setCode(ExceptionCode.INVALID_REQUEST);
        exception.setClientMessage("Parameter '{}' is missed.", parameterName);
        return exception;
    }

    public static QueueBrainException invalidReglink() {
        QueueBrainException exception = new QueueBrainException("Invalid reglink");
        exception.setCode(ExceptionCode.INVALID_REQUEST);
        exception.setClientMessage("Invalid reglink.");
        return exception;
    }

    public static QueueBrainException alreadyRegistered() {
        QueueBrainException exception = new QueueBrainException("User already registered");
        exception.setCode(ExceptionCode.INVALID_REQUEST);
        exception.setClientMessage("User has been already registered.");
        return exception;
    }

    public static QueueBrainException internal(String message) {
        QueueBrainException exception = new QueueBrainException(message);
        exception.setCode(ExceptionCode.COMMON_TECHNICAL);
        exception.setClientMessage("Internal server error.");
        return exception;
    }

    private static QueueBrainException somethingNotFound(String what, long id) {
        QueueBrainException exception = new QueueBrainException("Not found");
        exception.setCode(ExceptionCode.NOT_FOUND);
        exception.setClientMessage("{} with id '{}' not found.", what, id);
        return exception;
    }
}
