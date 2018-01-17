package com.zoxal.queuebrain.repository;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.exceptions.QueueBrainException;
import com.zoxal.queuebrain.model.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Queues repository
 *
 * @author Mike
 * @version 01/13/2018
 */
@Repository
public class QueueRepository {
    private QueueRepositoryBase base;

    @Autowired
    public QueueRepository(QueueRepositoryBase base) {
        this.base = base;
    }

    public Queue getQueue(long id) throws QueueBrainException {
        return base.findOne(id).orElseThrow(
            () -> ExceptionFactory.noSuchQueueException(id)
        );
    }

    public long count() {
        return base.count();
    }

    public void save(Queue queue) {
        base.save(queue);
    }

    public void delete(Long id) {
        base.delete(id);
    }
}