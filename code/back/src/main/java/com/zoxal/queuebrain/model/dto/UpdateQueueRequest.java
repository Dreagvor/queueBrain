package com.zoxal.queuebrain.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zoxal.queuebrain.model.Queue;

public class UpdateQueueRequest {
    private Queue.QueueState state;

    @JsonProperty(required = true)
    public Queue.QueueState getState() {
        return state;
    }

    public void setState(Queue.QueueState state) {
        this.state = state;
    }
}
