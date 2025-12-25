package com.example.__WebFlux.domain.auth.models;

import java.util.UUID;

public class UserRolDomain {
    private Long id;

    private UUID userId;

    private UserRol role;

    public UserRolDomain() {
    }

    public UserRolDomain(Long id, UUID userId, UserRol role) {
        this.id = id;
        this.userId = userId;
        this.role = role;
    }

    public UUID getUser_id() {
        return userId;
    }

    public UserRol getUserRol() {
        return this.role;
    }

    public void setUserRol(UserRol userRol) {
        this.role = userRol;
    }

    public void setUser_id(UUID user_id) {
        this.userId = user_id;
    }
}
