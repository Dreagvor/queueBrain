package com.zoxal.queuebrain.security.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.exceptions.QueueBrainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 01/16/2018
 */

public class QueueBrainAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger logger = LoggerFactory.getLogger(QueueBrainAccessDeniedHandler.class);
    private final ObjectMapper objectMapper;

    public QueueBrainAccessDeniedHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        logger.debug("Access denied.");
        QueueBrainException queueBrainException = ExceptionFactory.forbidden();
        objectMapper.writeValue(response.getOutputStream(), queueBrainException);
    }
}
