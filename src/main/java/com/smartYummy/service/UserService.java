package com.smartYummy.service;

import com.smartYummy.model.User;

/**
 * Created by chenglongwei on 4/21/16.
 */
public interface UserService {
    User findByEmail(String email);
    User findByID(long id);
    User insertUser(User user);
    void deleteById(long id);
}
