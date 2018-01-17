package com.zoxal.queuebrain.security.service;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.model.User;
import com.zoxal.queuebrain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Authenticates user by email and password
 *
 * @author Mike
 * @version 01/16/2018
 */
public class EmailPassAuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(EmailPassAuthProvider.class);
    private UserRepository userRepository;

    public EmailPassAuthProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getDetails() != null) return authentication;

        logger.debug("trying to authenticate by email and password ... ");
        UsernamePasswordAuthenticationToken upToken = (UsernamePasswordAuthenticationToken)authentication;
        String email = (String)upToken.getPrincipal();
        String password = (String)upToken.getCredentials();
        logger.debug("email: {}, password: {}, details: {}", email, password, authentication.getDetails());
        User user = userRepository.findUser(email, password);
        if (!user.isActive()) throw ExceptionFactory.invalidCredentials();
        upToken.setDetails(user);
        return upToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
