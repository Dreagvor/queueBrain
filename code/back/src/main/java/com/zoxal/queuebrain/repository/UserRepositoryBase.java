package com.zoxal.queuebrain.repository;

import com.zoxal.queuebrain.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * Users repository
 *
 * @author Mike
 * @version 01/08/2018
 */
public interface UserRepositoryBase extends Repository<User, Long> {
    Optional<User> findOne(Long id);
    Optional<User> findByRegistrationLink(String registrationLink);
    Optional<User> findByEmailAndPassword(String email, String password);
    long count();
    void save(User user);
    void delete(Long id);
}
