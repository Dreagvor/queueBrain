package com.zoxal.queuebrain.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Common controller exception interceptor
 *
 * @author Mike
 * @version 12/25/2017
 */
@RestControllerAdvice({
        "com.zoxal.queuebrain.api.rest",
        "com.zoxal.queuebrain.security.api"
})
public class ExceptionResolvingControllerAdvice {
    private static Logger logger = LoggerFactory.getLogger(ExceptionResolvingControllerAdvice.class);
    private List<ExceptionConverter<? extends Throwable>> convertersList = new LinkedList<>();

    @Autowired
    public ExceptionResolvingControllerAdvice() {
        logger.debug("Ama live!");
        // all converters configuration goes here
        convertersList.add(new ExceptionConverter<>(JsonParseException.class, (e) -> {
            QueueBrainException queueBrainException = new QueueBrainException("Json parsing exception", e);
            queueBrainException.setCode(ExceptionCode.INVALID_REQUEST);
            queueBrainException.setClientMessage(e.getMessage());
            return queueBrainException;
        }));

        convertersList.add(new ExceptionConverter<>(MethodArgumentTypeMismatchException.class, (e) -> {
            QueueBrainException queueBrainException = new QueueBrainException("Request param validation exception", e);
            queueBrainException.setCode(ExceptionCode.INVALID_REQUEST);
            String clientMessage = e.getMessage();
            if (e.getCause() != null && e.getCause() instanceof NumberFormatException) {
                clientMessage = e.getCause().getMessage();
            }
            queueBrainException.setClientMessage(clientMessage);
            return queueBrainException;
        }));

        convertersList.add(new ExceptionConverter<>(Exception.class, (e) -> {
            QueueBrainException queueBrainException = new QueueBrainException("Unexpected exception", e);
            queueBrainException.setCode(ExceptionCode.COMMON_TECHNICAL);
            queueBrainException.setClientMessage("Internal server error");
            return queueBrainException;
        }));
    }

    /**
     * Top-level exception handler. Every error, that will be thrown to the
     * client, will pass through this handler.
     *
     * @param response      http response. Used to set status code
     * @param e             exception itself
     * @return              QueueBrain exception object
     */
    @ExceptionHandler
    public QueueBrainException resolveQueueBrainException(HttpServletResponse response, QueueBrainException e) {
        logException(e);
        response.setStatus(e.getCode().getResponseStatus());
        return e;
    }

    /**
     * Iterates through  converters list trying to find appropriate one.
     * If there is no appropriate converter, uses the last one
     *
     * @param response      http servlet response. Need to set status code
     * @param e             handled exception itself
     * @return              converted QueueBrainException
     */
    @ExceptionHandler
    public QueueBrainException resolveCommonException(HttpServletResponse response, Exception e) {
        for (ExceptionConverter<? extends Throwable> converter : convertersList) {
            QueueBrainException convertedException = converter.convert(e);
            if (convertedException != null) {
                return resolveQueueBrainException(response, convertedException);
            }
        }
        // unreachable code
        return convertersList.get(convertersList.size() - 1).convert(e);
    }

    public static void logException(QueueBrainException e) {
        logger.error("UUID: {}, code: {}, clientMessage: {}",
                e.getUuid(), e.getCode().getCode(), e.getClientMessage(), e);
    }

    /**
     * Type-safe container for Throwable -> QueueBrain converting logic.
     *
     * @param <T>       exception type to convert from
     */
    private class ExceptionConverter<T extends Throwable> {
        private Class<T> exceptionClass;
        private Function<T, QueueBrainException> handler;

        /**
         * @param exceptionClass    exception class to convert from
         * @param handler           converting strategy function
         */
        public ExceptionConverter(Class<T> exceptionClass, Function<T, QueueBrainException> handler) {
            this.exceptionClass = exceptionClass;
            this.handler = handler;
        }

        /**
         * Tries to convert specified exception using itself or any its' cause.
         *
         * @param exception     exception to convert
         * @return              converted QueueBrainException
         *                      or null if there is no exception with specified type at cause hierarchy
         */
        public QueueBrainException convert(Throwable exception) {
            if (exception == null) return null;
            if (exceptionClass.isInstance(exception)) return handler.apply(exceptionClass.cast(exception));
            return convert(exception.getCause());
        }
    }
}