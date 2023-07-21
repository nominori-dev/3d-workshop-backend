package com.nominori.oms.infrastructure.security;

import com.nominori.oms.application.user.UserService;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class PostAuthenticationFilter implements Filter {

    private final UserService userService;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;

        if(token.isAuthenticated()){
            userService.getById(UUID.fromString(token.getTokenAttributes().get("sub").toString()));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
