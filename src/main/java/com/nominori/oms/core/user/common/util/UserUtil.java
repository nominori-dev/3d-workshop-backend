package com.nominori.oms.core.user.common.util;

import com.nominori.oms.core.user.common.User;
import com.nominori.oms.application.user.UserService;
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

