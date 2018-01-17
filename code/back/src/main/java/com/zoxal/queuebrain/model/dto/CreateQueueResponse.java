package com.zoxal.queuebrain.model.dto;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 01/17/2018
 */
public class CreateQueueResponse {
    private long queueId;

    public CreateQueueResponse(long queueId) {
        this.queueId = queueId;
    }

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }
}
