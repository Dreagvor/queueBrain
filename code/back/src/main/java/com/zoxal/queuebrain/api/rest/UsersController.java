package com.zoxal.queuebrain.api.rest;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.model.Queue;
import com.zoxal.queuebrain.model.User;
import com.zoxal.queuebrain.model.dto.*;
import com.zoxal.queuebrain.repository.UserRepository;
import com.zoxal.queuebrain.repository.UserRepositoryBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Provides API to work with users
 *
 * @author Mike
 * @version 12/12/2017
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Transactional
public class UsersController {
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    private UserRepository userRepository;

    @Autowired
    public UsersController(UserRepository repository) {
        logger.debug("Users controller created with repository: {}", repository);
        this.userRepository = repository;
    }

    @GetMapping
    public GetUsersStatisticResponse getStatistics() {
        logger.trace("Getting users statistics");
        GetUsersStatisticResponse response = new GetUsersStatisticResponse();
        response.setUsersCount(userRepository.count());
        return response;
    }

    @GetMapping("/me")
    public GetUserResponse getMyself() {
        logger.trace("Getting myself");
        User loginedUser = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.getUser(loginedUser.getId());
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setContacts(user.getContacts());
        response.setManagedQueues(user.getManagedQueues().stream().map(Queue::getId).collect(Collectors.toList()));
        response.setUsingQueues(user.getUsingQueues().stream().map(Queue::getId).collect(Collectors.toList()));
        return response;
    }

    @GetMapping("/{id}")
    public GetUserResponse getUser(@PathVariable("id") long id) {
        logger.trace("Getting user {}", id);
        User user = userRepository.getUser(id);
        GetUserResponse response = new GetUserResponse();
        response.setId(id);
        response.setName(user.getName());
        response.setContacts(user.getContacts());
        response.setManagedQueues(user.getManagedQueues().stream().map(Queue::getId).collect(Collectors.toList()));
        response.setUsingQueues(user.getUsingQueues().stream().map(Queue::getId).collect(Collectors.toList()));
        return response;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest req) {
        logger.debug("Creating user: {}", req.getName());
        User user = new User();
        user.setName(req.getName());
        user.setContacts(req.getContacts());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());
        userRepository.save(user);

        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        return response;
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") long id, @RequestBody UpdateUserRequest req) {
        logger.debug("Updating user {}", id);
        User user = new User();
        if (req.getContacts() != null) {
            user.setContacts(req.getContacts());
        }
        if (req.getPassword() != null) {
            user.setPassword(req.getPassword());
        }
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        logger.debug("Deleting user {}", id);
        userRepository.delete(id);
    }
}
