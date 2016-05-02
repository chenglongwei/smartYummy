package com.smartYummy.service;

import com.smartYummy.model.User;

import java.util.Optional;

/**
 * Created by chenglongwei on 4/21/16.
 */
public interface UserService {
    Optional<User> findByEmail(String email);
    User findByID(long id);
    User insertUser(User user);
    void deleteById(long id);
}
