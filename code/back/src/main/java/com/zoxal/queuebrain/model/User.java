package com.zoxal.queuebrain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zoxal.queuebrain.security.utils.RegistrationLinkGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents User in application
 *
 * @author Mike
 * @version 12/12/2017
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String contacts;
    private String email;
    private String password;
    private boolean isActive = false;
    private String registrationLink;
    @ManyToMany
    @JoinTable(
            name="USERS_QUEUES",
            joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="QUEUE_ID", referencedColumnName="ID"))
    private List<Queue> usingQueues = new ArrayList<>();
    @OneToMany(mappedBy="admin")
    private List<Queue> managedQueues = new ArrayList<>();

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRegistrationLink() {
        return registrationLink;
    }

    public void setRegistrationLink(String registrationLink) {
        this.registrationLink = registrationLink;
    }
}
