# Security

### Order Management Application


## Security Model

Main backbone of the security – Spring Security 6. This is default solution to protect Spring Framework
based application, but Spring Security could be used without Spring itself. Security model is designed to
be as less dependent from framework and IdP as possible, that means we can easily switch to another IdP
or framework without doing a lot of useless work. Everything except UID and E-Mail is stored on the
Identity Provider side, application database stores only UID and E-mail.

## Authentication Process

The authentication process is default as possible. On the server-side we have a “Resource Server” which
validates tokens from HTTP requests. Tokens (or Access Tokens) which are obtained from Keycloak (IdP).
There is a need to synchronize users between the IdP database and the application database. This is
necessary to connect the user with other database entities such as orders. But we should not store user
information such as Name, Birth Day, etc, because it could violate GPDR or other privacy regulations. So
everything what we need is UID (User ID) and E-Mail.

To sync users there is custom “PostAuthenticationFilter” which implements Jakarta Servlet Filter. Step-by-
step filter process:

1. Get token from authentication context and do null-checks.
2. Get UID from token and execute “UserService” method to get user by UID. This method uses Java
   8 Optional API to create new user with provided UID if there is no present.

## Authorities

Application uses RBAC security method to protect API endpoints based on individual user roles. Roles are
granted to users by Identity Provider (Keycloak) and encapsulated in access token. Access token is being
placed in HTTP Header – “Authentication” : “Bearer ${token}”. Spring Security Filter Chains are processing
incoming HTTP requests. To map roles from access token to user in Spring Security Context - application
uses custom authority mapper declared as “JwtAuthConverter”.

Step-by-step role mapping procedure:

1. Get list of realm roles and do null-checks.
2. Iterate through role list and add them to granted authorities. Spring Framework roles are using
   “ROLE_” prefix, by using “RoleConverter” we convert non-Spring roles by adding
   “ROLE_REALM_${role_name}” prefix.
3. Get list of resource roles and do null-checks.
4. Repeat step 2, but with resource access roles.
5. Return collection of “GrantedAuthorities”.

Resource name is “API” and Spring Security annotations on the endpoints are using roles that defined in
this list, any other roles are not used and will be ignored by application.

```
Role Name               Role Description
----------------------  ----------------------------------
ROLE_API_USER           Default user. Does not has access to management API.
ROLE_API_CUSTOMER       Default user as ROLE_API_USER, but has access only to Order API.
ROLE_API_ADMIN          Highest management role, has access to all API’s.
ROLE_API_PRODUCT_ADMIN  Management role, has access to File Cloud API,Order API, Product API.
```


