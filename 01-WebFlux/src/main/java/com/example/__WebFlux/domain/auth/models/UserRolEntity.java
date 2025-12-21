package com.example.__WebFlux.domain.auth.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "user_roles")
public class UserRolEntity {

    @Id
    private Long id;

    @Column("user_id")
    private UUID userId;

    @Column("role")
    private UserRol role;

    public UserRolEntity() {
    }

    public UserRolEntity(UUID userId, UserRol role) {
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
