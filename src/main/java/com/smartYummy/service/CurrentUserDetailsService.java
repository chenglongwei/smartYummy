package com.smartYummy.service;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by chenglongwei on 5/1/16.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        Optional<User> user = userService.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with email=%s was not found", email));
        }
        return new CurrentUser(user.get());
    }
}
