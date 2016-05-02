package com.smartYummy.service;

import com.smartYummy.model.CurrentUser;

/**
 * Created by chenglongwei on 5/1/16.
 */
public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
