package com.zoxal.queuebrain.model;

/**
 * Represents Comment
 *
 * @author Mike
 * @version 12/12/2017
 */
public class Comment {
    private User author;
    private Queue queue;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }
}
