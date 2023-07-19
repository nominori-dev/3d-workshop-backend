package com.nominori.oms.user.common.util;

import com.nominori.oms.user.common.model.User;
import com.nominori.oms.user.common.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserUtil {

    private final UserService userService;

    public User getUserFromSecurityContext(){
        JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return userService.getById(UUID.fromString(token.getTokenAttributes().get("sub").toString()));
    }


}

