package com.example.__WebFlux.infrastructure.auth.persistence;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.example.__WebFlux.domain.auth.models.UserRol;

import lombok.Data;

@Table(name = "users")
@Data
public class UserModelEntity{
    @Id
    private UUID id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String passwordHash;

    @Column("rols")
    private Set<UserRol> rols;
}
