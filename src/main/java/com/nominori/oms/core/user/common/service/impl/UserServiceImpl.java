package com.nominori.oms.core.user.common.service.impl;

import com.nominori.oms.core.user.common.UserRepository;
import com.nominori.oms.core.user.common.service.UserService;
import com.nominori.oms.core.user.common.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getById(UUID id) {
        return userRepository.findById(id)
                .orElseGet(() -> save(id));
    }

    // #TODO Refactor this code cuz it's cringe
    private User save(UUID id){
        final var auth = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        final var token = auth.getToken();

        if(token.hasClaim("sub")){
            String email = token.getClaimAsString("email");

            if (!id.equals(UUID.fromString(token.getClaim("sub")))) {
                throw new RuntimeException("Invalid User ID.");
            }

            return userRepository.save(new User(id, email));
        }else{
            throw new RuntimeException("Token validation failed.");
        }
    }


}
