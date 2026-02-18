package com.example.webflux.domain.emailVerificationToken.models;

import java.time.Instant;
import java.util.UUID;

public final class EmailVerificationTokenModel {
    private final UUID id;
    private final UUID userId;
    private final String tokenHash;
    private final Instant expiredAt;
    private final Instant consumedAt;
    private final Instant createAt;

    private EmailVerificationTokenModel(UUID id, UUID userId, String tokenHash, Instant expiredAt, Instant consumedAt,
            Instant createAt) {
        this.id = id;
        this.userId = userId;
        this.tokenHash = tokenHash;
        this.expiredAt = expiredAt;
        this.consumedAt = consumedAt;
        this.createAt = createAt;
    }

    public static EmailVerificationTokenModel createNew(UUID userId, String tokenHash, Instant expiredAt, Instant consumedAt,
            Instant createAt) {
        return new EmailVerificationTokenModel(UUID.randomUUID(), userId, tokenHash, expiredAt, consumedAt, createAt);
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public Instant getExpiredAt() {
        return expiredAt;
    }

    public Instant getConsumedAt() {
        return consumedAt;
    }

    public Instant getCreateAt() {
        return createAt;
    }

}
