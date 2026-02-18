package com.example.webflux.infrastructure.auth.persistence;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name = "users")
@Data
public class UserModelEntity {
    @Id
    @Column("user_id")
    private UUID id;

    @Column("username")
    private String username;

    @Column("auth_status")
    private String authStatus;

    @Column("email")
    private String email;

    @Column("password_hash")
    private String passwordHash;
}
