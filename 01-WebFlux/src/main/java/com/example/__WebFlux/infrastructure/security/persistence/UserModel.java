package com.example.__WebFlux.infrastructure.security.persistence;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name = "users")
@Data
public class UserModel{
    @Id
    private String id;

    @Column("username")
    private String username;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("rols")
    private List<String> rols;
}
