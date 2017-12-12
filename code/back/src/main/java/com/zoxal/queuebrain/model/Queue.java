package com.zoxal.queuebrain.model;

import java.util.List;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 12/12/2017
 */
public class Queue {
    private String name;
    private List<User> users;
    private User admin;
    private QueueState state;

    enum QueueState {
        NOT_STARTED, ACTIVE, PAUSED, FINISHED
    }
}
