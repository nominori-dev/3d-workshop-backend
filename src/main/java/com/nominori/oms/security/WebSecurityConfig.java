package com.nominori.oms.security;

import com.nominori.oms.user.common.service.UserService;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfig {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String CUSTOMER = "CUSTOMER";

    private final JwtAuthConverter jwtAuthConverter;

    private final UserService userService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter)));
        http.addFilterAfter(postAuthFilter(), BearerTokenAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public Filter postAuthFilter(){
        return new PostAuthenticationFilter(userService);
    }

}
