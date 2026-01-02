package com.example.__WebFlux.infrastructure.security;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.__WebFlux.domain.auth.models.UserModelDomain;

public class CustomUserDetails implements UserDetails {

    private UUID userId;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserModelDomain userModel) {
        this.userId = userModel.getId();
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
        this.authorities = userModel.getRols()
                .stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                .toList();
    }

    public UUID getUserId() {
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
