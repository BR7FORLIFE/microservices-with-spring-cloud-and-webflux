package com.example.__WebFlux.domain.refreshToken.models;

import java.time.Instant;

public class RefreshToken {
    private String id;
    private String userId;
    private String tokenHash;
    private Instant expiresAt;
    private boolean revoked;
    private Instant createAt;

    public RefreshToken() {
    }

    public RefreshToken(String id, String userId, String tokenHash, Instant expiresAt, boolean revoked,
            Instant createAt) {
        this.id = id;
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.revoked = revoked;
        this.createAt = createAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public RefreshToken markRevoked() {
        return new RefreshToken(id, userId, tokenHash, expiresAt, revoked, createAt);
    }

    public boolean isExpired(Instant now) {
        return now.isAfter(expiresAt);
    }
}
