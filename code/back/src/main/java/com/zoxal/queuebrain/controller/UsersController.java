package com.zoxal.queuebrain.controller;

import com.zoxal.queuebrain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 12/12/2017
 */
@RestController("/users")
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @GetMapping
    public @ResponseBody List<User> getAllUsers() {
        logger.debug("getting all users");
        List<User> users = new ArrayList<User>(2);
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
