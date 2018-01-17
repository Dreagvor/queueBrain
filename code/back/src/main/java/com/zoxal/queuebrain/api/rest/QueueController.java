package com.zoxal.queuebrain.api.rest;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.model.Queue;
import com.zoxal.queuebrain.model.User;
import com.zoxal.queuebrain.model.dto.*;
import com.zoxal.queuebrain.repository.QueueRepository;
import com.zoxal.queuebrain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * Queues controller
 *
 * @author Mike
 * @version 12/26/2017
 */
@RestController
@RequestMapping("/queues")
@CrossOrigin
@Transactional
public class QueueController {
    private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

    private final QueueRepository queueRepository;
    private final UserRepository userRepository;

    @Autowired
    public QueueController(QueueRepository queueRepository, UserRepository userRepository) {
        this.queueRepository = queueRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public GetQueuesStatisticResponse getQueuesStatistic() {
        logger.debug("Gathering queues statistics");
        GetQueuesStatisticResponse response = new GetQueuesStatisticResponse();
        response.setQueuesCount(queueRepository.count());
        return response;
    }

    @GetMapping("/{id}")
    public GetQueueResponse getQueue(@PathVariable("id") long id) {
        logger.debug("Retrieving queue: {}", id);
        Queue queue = queueRepository.getQueue(id);
        GetQueueResponse response = new GetQueueResponse();
        response.setId(id);
        response.setName(queue.getName());
        response.setAdmin(queue.getAdmin().getId());
        response.setCurrentUser(queue.getCurrentUser());
        response.setUsers(queue.getUsers().stream().map(User::getId).collect(Collectors.toList()));
        return response;
    }

    @PostMapping
    public CreateQueueResponse createQueue(@RequestBody CreateQueueRequest request) {
        logger.debug("Creating queue: {}", request);
        if (request.getName() == null) {
            throw ExceptionFactory.parameterMissed("name");
        }
        Queue queue = new Queue();
        queue.setName(request.getName());
        User loginedUser = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.getUser(loginedUser.getId());
        queue.setAdmin(user);
        user.getManagedQueues().add(queue);
        userRepository.save(user);
        queueRepository.save(queue);
        return new CreateQueueResponse(queue.getId());
    }

    @PostMapping("{id}/enter")
    public void enterQueue(@PathVariable("id") long id) {
        logger.debug("Entering queue: {}", id);
        Queue queue = queueRepository.getQueue(id);
        User loginedUser = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userRepository.getUser(loginedUser.getId());
        queue.getUsers().add(user);
        user.getUsingQueues().add(queue);
        userRepository.save(user);
        queueRepository.save(queue);
    }

    @PutMapping("/{id}")
    public void pushQueue(@PathVariable("id") long id) {
        logger.debug("Pushing queue {}", id);
        Queue queue = queueRepository.getQueue(id);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (queue.getAdmin().getId() != user.getId()) {
            throw ExceptionFactory.forbidden();
        }
        int currentUser = queue.getCurrentUser();
        if (currentUser < queue.getUsers().size()) {
            queue.setCurrentUser(currentUser + 1);
        } else {
            throw ExceptionFactory.invalidClientParameter("Can not push queue: no more users.");
        }
        queueRepository.save(queue);
    }

//    @PutMapping("/{id}")
//    public void updateQueue(@PathVariable("id") long id, UpdateQueueRequest request) {
//        logger.debug("Updating queue {}", id);
//        Queue queue = queueRepository.getQueue(id);
//        Queue.QueueState newState = request.getState();
//        if (queue.getState().validateSwitch(newState)) {
//            queue.setState(newState);
//            queue.setStartedTime(Calendar.getInstance().getTime());
//        } else {
//            throw ExceptionFactory.invalidClientParameter("state", newState.name());
//        }
//        queueRepository.save(queue);
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        logger.debug("Deleting queue {}", id);
        queueRepository.delete(id);
    }
}
