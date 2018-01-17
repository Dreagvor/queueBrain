package com.zoxal.queuebrain.model.dto;

import com.zoxal.queuebrain.model.User;

import java.util.List;

public class GetUserResponse {
    private long id;
    private String name;
    private String contacts;
    private List<Long> usingQueues;
    private List<Long> managedQueues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<Long> getUsingQueues() {
        return usingQueues;
    }

    public void setUsingQueues(List<Long> usingQueues) {
        this.usingQueues = usingQueues;
    }

    public List<Long> getManagedQueues() {
        return managedQueues;
    }

    public void setManagedQueues(List<Long> managedQueues) {
        this.managedQueues = managedQueues;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
