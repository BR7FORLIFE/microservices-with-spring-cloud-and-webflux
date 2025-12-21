package com.example.__WebFlux.domain.auth.models;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserModelDomain {
    private UUID id;
    private String username;
    private String email;
    private String passwordHash;
    private Set<UserRol> rols;

    public UserModelDomain() {
    }

    public UserModelDomain(UUID id, String username, String email, String password, Set<UserRol> rols) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = password;
        this.rols = rols;
    }

    public static UserModelDomain register(String username, String email, String passwordHash) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("the username is null!");
        }

        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("the email is null!");
        }

        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("not password!");
        }

        return new UserModelDomain(UUID.randomUUID(), username, email, passwordHash, Set.of(UserRol.USER));
    }

    public boolean passwordMatches(String raw, PasswordEncoder encoder) {
        return encoder.matches(raw, passwordHash);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.passwordHash = password;
    }

    public Set<UserRol> getRols() {
        return rols;
    }

    public void setRols(Set<UserRol> rols) {
        this.rols = rols;
    }
}
