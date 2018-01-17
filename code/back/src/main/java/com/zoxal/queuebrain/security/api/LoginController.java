package com.zoxal.queuebrain.security.api;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.repository.UserRepository;
import com.zoxal.queuebrain.security.dto.FirstLoginRequest;
import com.zoxal.queuebrain.security.dto.LoginPassLoginRequest;
import com.zoxal.queuebrain.security.dto.SuccessLoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login service
 *
 * @author Mike
 * @version 01/08/2018
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;


    @Autowired
    public LoginController(UserRepository userRepository,
                           AuthenticationManager authenticationManager,
                           SecurityContextRepository securityContextRepository) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @PostMapping
    @RequestMapping(params = {"type=loginpass"})
    public SuccessLoginResponse loginViaLoginPass(@RequestBody LoginPassLoginRequest request,
                                                  HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        logger.debug("login via login-pass");
        Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        authenticate(auth, servletRequest, servletResponse);
        return successLogin();
    }

    @PostMapping
    @RequestMapping(params = {"type=first"})
    public SuccessLoginResponse firstLogin(@RequestBody FirstLoginRequest request,
                                           HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        logger.debug("login via reglink {}", request.getRegistrationLink());
        RegistrationLinkAuthToken auth = new RegistrationLinkAuthToken(null, null);
        auth.setRegistrationLink(request.getRegistrationLink());
        authenticate(auth, servletRequest, servletResponse);
        return successLogin();
    }

    private void authenticate(Authentication baseAuth,
                                        HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Authentication authResult;
        try {
            logger.debug("trying to authenticate...");
            authResult = authenticationManager.authenticate(baseAuth);
        } catch (Exception e) {
            logger.warn("can not authenticate :(", e);
            throw ExceptionFactory.invalidCredentials();
        }
        SecurityContextHolder.getContext().setAuthentication(authResult);
        securityContextRepository.saveContext(SecurityContextHolder.getContext(), servletRequest, servletResponse);
    }

    private SuccessLoginResponse successLogin() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

        SuccessLoginResponse response = new SuccessLoginResponse();
        response.setSessionId(sessionId);
        return response;
    }
}
