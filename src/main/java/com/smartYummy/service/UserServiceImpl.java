package com.smartYummy.service;

import com.smartYummy.model.User;
import com.smartYummy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by chenglongwei on 4/21/16.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByID(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepository.delete(id);
    }
}
