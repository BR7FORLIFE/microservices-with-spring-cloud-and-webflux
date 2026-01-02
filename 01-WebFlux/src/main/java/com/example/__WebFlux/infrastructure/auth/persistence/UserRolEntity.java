package com.example.__WebFlux.infrastructure.auth.persistence;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "rols")
public class UserRolEntity {

    @Id
    @Column("rol_id")
    private Long id;

    @Column("user_id")
    private UUID userId;

    @Column("rol")
    private String role;

    public UserRolEntity() {
    }

    public UserRolEntity(UUID userId, String role) {
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
