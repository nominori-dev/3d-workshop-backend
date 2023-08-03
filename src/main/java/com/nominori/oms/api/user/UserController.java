package com.nominori.oms.api.user;

import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/user/")
public class UserController {

    @Profile("dev")
    @PreAuthorize("hasAnyRole({'OMS_ADMIN', 'OMS_USER'})")
    @GetMapping("/roles")
    public Collection<SimpleGrantedAuthority> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Collection<SimpleGrantedAuthority>) authentication.getAuthorities();
    }

}
