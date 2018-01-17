package com.zoxal.queuebrain.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.exceptions.ExceptionResolvingControllerAdvice;
import com.zoxal.queuebrain.exceptions.QueueBrainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * If there is no authentication in request, throws exception
 *
 * @author Mike
 * @version 01/16/2018
 */
public class QueueBrainAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(QueueBrainAuthenticationEntryPoint.class);
    private final ObjectMapper objectMapper;

    public QueueBrainAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.debug("Access denied.");
        QueueBrainException queueBrainException = ExceptionFactory.forbidden();
        ExceptionResolvingControllerAdvice.logException(queueBrainException);
        objectMapper.writeValue(response.getOutputStream(), queueBrainException);
    }
}
