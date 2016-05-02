package com.smartYummy.service;

import com.smartYummy.model.CurrentUser;
import com.smartYummy.model.Role;
import org.springframework.stereotype.Service;

/**
 * Created by chenglongwei on 5/1/16.
 */
@Service
public class CurrentUserServiceImpl implements CurrentUserService {
    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }
}
