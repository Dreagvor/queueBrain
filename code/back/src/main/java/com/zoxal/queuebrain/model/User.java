package com.zoxal.queuebrain.model;

import java.util.List;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 12/12/2017
 */
public class User {
    private String name;
    private String contacts;
    private List<Queue> managedQueues;
    private List<Queue> usingQueues;

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

    public List<Queue> getManagedQueues() {
        return managedQueues;
    }

    public void setManagedQueues(List<Queue> managedQueues) {
        this.managedQueues = managedQueues;
    }

    public List<Queue> getUsingQueues() {
        return usingQueues;
    }

    public void setUsingQueues(List<Queue> usingQueues) {
        this.usingQueues = usingQueues;
    }
}
