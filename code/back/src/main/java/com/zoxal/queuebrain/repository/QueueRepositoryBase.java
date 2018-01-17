package com.zoxal.queuebrain.repository;

import com.zoxal.queuebrain.model.Queue;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * SpringData-based repository base interface
 *
 * @author Mike
 * @version 01/13/2018
 */
public interface QueueRepositoryBase extends Repository<Queue, Long> {
    Optional<Queue> findOne(Long id);
    long count();
    void save(Queue queue);
    void delete(Long id);
}