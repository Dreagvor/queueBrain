package com.zoxal.queuebrain.repository;

import com.zoxal.queuebrain.exceptions.ExceptionFactory;
import com.zoxal.queuebrain.exceptions.QueueBrainException;
import com.zoxal.queuebrain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Users repository
 *
 * @author Mike
 * @version 01/13/2018
 */
@Repository
public class UserRepository {
    private final UserRepositoryBase base;

    @Autowired
    public UserRepository(UserRepositoryBase base) {
        this.base = base;
    }

    public User getUser(long id) throws QueueBrainException {
        return base.findOne(id).orElseThrow(() -> ExceptionFactory.noSuchUserException(id));
    }

    public User findUser(String email, String password) {
        return base.findByEmailAndPassword(email, password).orElseThrow(() -> ExceptionFactory.invalidCredentials());
    }

    public User getUserByReglink(String reglink) throws QueueBrainException {
        return base.findByRegistrationLink(reglink).orElseThrow(ExceptionFactory::invalidReglink);
    }

    public long count() {
        return base.count();
    }

    public void save(User user) {
        base.save(user);
    }

    public void delete(Long id) {
        base.delete(id);
    }
}
