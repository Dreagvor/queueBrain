package com.zoxal.queuebrain.security.service;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.model.User;
import com.zoxal.queuebrain.repository.UserRepository;
import com.zoxal.queuebrain.security.api.RegistrationLinkAuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Authenticates user by email and password
 *
 * @author Mike
 * @version 01/16/2018
 */
public class ReglinkAuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(ReglinkAuthProvider.class);
    private UserRepository userRepository;

    public ReglinkAuthProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.getDetails() != null) return authentication;

        RegistrationLinkAuthToken upToken = (RegistrationLinkAuthToken)authentication;
        String reglink = upToken.getRegistrationLink();
        logger.debug("Trying to authenticate by reglink {}... ", reglink);
        User user = userRepository.getUserByReglink(reglink);
        if (user.isActive()) {
            throw ExceptionFactory.alreadyRegistered();
        } else {
            user.setActive(true);
            userRepository.save(user);
        }

        upToken.setDetails(user);

        return upToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RegistrationLinkAuthToken.class.equals(authentication);
    }
}
