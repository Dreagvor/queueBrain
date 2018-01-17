package com.zoxal.queuebrain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents queue in application
 *
 * @author Mike
 * @version 12/12/2017
 */
@Entity
public class Queue {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @ManyToMany(mappedBy="usingQueues")
    private List<User> users = new ArrayList<>();
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="ADMIN_ID")
    private User admin;
    private int currentUser;
    private QueueState state = QueueState.NOT_STARTED;
    private Date startedTime;
//    private List<Comment> comments;

    public enum QueueState {
        NOT_STARTED, ACTIVE, PAUSED, FINISHED;

        public boolean validateSwitch(QueueState newState) {
            switch (this) {
                case NOT_STARTED: {
                    return ACTIVE.equals(newState);
                }
                case ACTIVE: {
                    return PAUSED.equals(newState) || FINISHED.equals(newState);
                }
                case PAUSED: {
                    return ACTIVE.equals(newState) || FINISHED.equals(newState);
                }
                case FINISHED: {
                    return false;
                }
                default: {
                    throw new IllegalArgumentException("QueueState " + this + "is not supported");
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public QueueState getState() {
        return state;
    }

    public void setState(QueueState state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int currentUser) {
        this.currentUser = currentUser;
    }

    public Date getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Date startedTime) {
        this.startedTime = startedTime;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }
}
