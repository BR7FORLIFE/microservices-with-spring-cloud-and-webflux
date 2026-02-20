package com.example.webflux.domain.auth.models;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserModelDomain {
    private UUID id;
    private String username;
    private String email;
    private String passwordHash;
    private UserAuthStatus authStatus;
    private Set<String> rols;

    public UserModelDomain() {
    }

    public UserModelDomain(UUID id, String username, UserAuthStatus status, String email, String password,
            Set<String> rols) {
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

        return new UserModelDomain(UUID.randomUUID(), username, UserAuthStatus.PENDING, email, passwordHash,
                Set.of("USER"));
    }

    public UserModelDomain activateUser() {
        if (this.authStatus == UserAuthStatus.ACTIVE) {
            throw new IllegalStateException("User already activate");
        }
        return new UserModelDomain(
                this.id,
                this.username,
                UserAuthStatus.ACTIVE,
                this.email,
                this.passwordHash,
                this.rols);
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

    public Set<String> getRols() {
        return rols;
    }

    public void setRols(Set<String> rols) {
        this.rols = rols;
    }

    public UserAuthStatus getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(UserAuthStatus authStatus) {
        this.authStatus = authStatus;
    }
}
