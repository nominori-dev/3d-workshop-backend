package com.nominori.oms.core.user.common.service;

import com.nominori.oms.core.user.common.User;

import java.util.UUID;

public interface UserService {
    User getById(UUID id);
}
