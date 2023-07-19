package com.nominori.oms.user.common.service;

import com.nominori.oms.user.common.model.User;

import java.util.UUID;

public interface UserService {
    User getById(UUID id);
}
