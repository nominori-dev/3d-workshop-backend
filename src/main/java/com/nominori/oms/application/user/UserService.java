package com.nominori.oms.application.user;

import com.nominori.oms.core.user.common.User;

import java.util.UUID;

public interface UserService {
    User getById(UUID id);
}
