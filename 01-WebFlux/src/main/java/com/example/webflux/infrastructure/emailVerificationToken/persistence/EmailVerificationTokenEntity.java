package com.example.webflux.infrastructure.emailVerificationToken.persistence;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table(name = "email_verification_token")
public class EmailVerificationTokenEntity {

    @Id
    @Column("email_verification_token_id")
    private UUID emailVerificationId;

    @Column("user_id")
    private UUID userId;

    @Column("token_hash")
    private String tokenHash;

    @Column("expired_at")
    private Instant expiredAt;

    @Column("consumed_at")
    private Instant consumedAt;

    @Column("create_at")
    private Instant createAt;
}
