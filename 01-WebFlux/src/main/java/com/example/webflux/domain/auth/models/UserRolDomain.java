package com.example.webflux.domain.auth.models;

import java.util.UUID;

public class UserRolDomain {
    private Long id;

    private UUID userId;

    private String role;

    public UserRolDomain() {
    }

    public UserRolDomain(Long id, UUID userId, String role) {
        this.id = id;
        this.userId = userId;
        this.role = role;
    }

    public UUID getUser_id() {
        return userId;
    }

    public String getUserRol() {
        return this.role;
    }

    public void setUserRol(String userRol) {
        this.role = userRol;
    }

    public void setUser_id(UUID userId) {
        this.userId = userId;
    }
}
