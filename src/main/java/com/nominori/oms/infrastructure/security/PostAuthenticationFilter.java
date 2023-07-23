package com.nominori.oms.infrastructure.security;

import com.nominori.oms.application.user.UserService;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class PostAuthenticationFilter implements Filter {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if an authentication object is present and if it's a JwtAuthenticationToken
        if (authentication != null && authentication instanceof JwtAuthenticationToken) {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;

            // Check if the token is authenticated (valid bearer token is provided)
            if (token.isAuthenticated()) {
                // Do your additional processing here, for example, fetching user by ID
                userService.getById(UUID.fromString(token.getTokenAttributes().get("sub").toString()));
            }
        }

        // Continue with the filter chain
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
