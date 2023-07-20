package com.nominori.oms.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class JwtAuthConverterTest {

    // Class under test
    private JwtAuthConverter converter = null;

    @BeforeEach
    public void before() {
        converter = new JwtAuthConverter();
    }

    @Test
    public void testNoRealmAccessClaim() {
        Jwt jwt = givenBaseToken().build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testEmptyRealmAccessClaim() {
        Map<String, Object> realmAccess = new HashMap<>();

        Jwt jwt = givenBaseToken().claim("realm_access", realmAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testEmptyRealmRoles() {
        Map<String, Object> realmAccess = new HashMap<>();
        Collection<String> roles = new ArrayList<>();
        realmAccess.put("roles", roles);

        Jwt jwt = givenBaseToken().claim("realm_access", realmAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testOneRealmRole() {
        Map<String, Object> realmAccess = new HashMap<>();
        Collection<String> roles = new ArrayList<>();
        roles.add("realm_role_1");
        realmAccess.put("roles", roles);

        Jwt jwt = givenBaseToken().claim("realm_access", realmAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).hasSize(1);
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_1"));
    }

    @Test
    public void testMultipleRealmRole() {
        Map<String, Object> realmAccess = new HashMap<>();
        Collection<String> roles = new ArrayList<>();
        roles.add("realm_ROLE_1");
        roles.add("realm_role_2");
        roles.add("realm_role_3");
        realmAccess.put("roles", roles);

        Jwt jwt = givenBaseToken().claim("realm_access", realmAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).hasSize(3);
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_1"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_2"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_3"));
    }

    @Test
    public void testNoResourceAccessClaim() {
        Jwt jwt = givenBaseToken().build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testEmptyResourceAccessClaim() {
        Map<String, Object> resourceAccess = new HashMap<>();

        Jwt jwt = givenBaseToken().claim("resource_access", resourceAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testEmptyResourceAccessRolesClaim() {
        Map<String, Object> resourceAccess = new HashMap<>();
        Map<String, Object> restApiResource = new HashMap<>();
        Collection<String> roles = new ArrayList<>();
        restApiResource.put("roles", roles);
        resourceAccess.put("rest-api", restApiResource);

        Jwt jwt = givenBaseToken().claim("resource_access", resourceAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).isEmpty();
    }

    @Test
    public void testOneResourceWithOneRole() {
        Map<String, Object> resourceAccess = new HashMap<>();
        Map<String, Object> restApiResource = new HashMap<>();
        Collection<String> roles = new ArrayList<>();
        roles.add("user");
        restApiResource.put("roles", roles);
        resourceAccess.put("rest-api", restApiResource);

        Jwt jwt = givenBaseToken().claim("resource_access", resourceAccess).build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).hasSize(1);
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REST_API_USER"));
    }

    @Test
    public void testRealmAndResourceRoles() {
        // Realm roles
        Map<String, Object> realmAccess = new HashMap<>();
        Collection<String> realmRoles = new ArrayList<>();
        realmRoles.add("realm-role-1");
        realmRoles.add("realm-role-2");
        realmRoles.add("realm-role-3");
        realmAccess.put("roles", realmRoles);

        // Resource roles
        Map<String, Object> resourceAccess = new HashMap<>();

        Map<String, Object> restApiResource = new HashMap<>();
        Collection<String> restApiRoles = new ArrayList<>();
        restApiRoles.add("user");
        restApiRoles.add("admin");
        restApiResource.put("roles",  restApiRoles);
        resourceAccess.put("rest-api", restApiResource);

        Map<String, Object> apiResource = new HashMap<>();
        Collection<String> apiRoles = new ArrayList<>();
        apiRoles.add("read");
        apiRoles.add("write");
        apiResource.put("roles", apiRoles);
        resourceAccess.put("api", apiResource);


        Jwt jwt = givenBaseToken()
                .claim("realm_access", realmAccess)
                .claim("resource_access", resourceAccess)
                .build();

        Collection<GrantedAuthority> authorities = converter.convert(jwt);

        assertThat(authorities).hasSize(7);

        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_1"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_2"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REALM_REALM_ROLE_3"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REST_API_USER"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_REST_API_ADMIN"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_API_READ"));
        assertThat(authorities).contains(new SimpleGrantedAuthority("ROLE_API_WRITE"));
    }

    /**
     * @return a JWT builder to build a valid token containing no claims
     */
    private Jwt.Builder givenBaseToken() {
        return Jwt.withTokenValue("tokenValue")
                .header("alg", "none")
                .audience(List.of("https://audience.example.org"))
                .expiresAt(Instant.MAX)
                .issuedAt(Instant.MIN)
                .issuer("https://issuer.example.org")
                .jti("jti")
                .notBefore(Instant.MIN)
                .subject("test-subject");
    }
}