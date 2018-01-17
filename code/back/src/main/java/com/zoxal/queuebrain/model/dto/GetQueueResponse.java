package com.zoxal.queuebrain.model.dto;

import java.util.Date;
import java.util.List;

public class GetQueueResponse {
    private long id;
    private String name;
    private long admin;
    private List<Long> users;
    private long currentUser;
    private Date startDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdmin() {
        return admin;
    }

    public void setAdmin(long admin) {
        this.admin = admin;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public long getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(long currentUser) {
        this.currentUser = currentUser;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
