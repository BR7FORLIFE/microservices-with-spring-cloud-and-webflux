package com.example.__WebFlux.infrastructure.refreshToken.persistence;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table
@Data
public class RefreshTokenEntity {
    @Id
    private String id;

    @Column("user_id")
    private String userId;

    @Column("token_hash")
    private String tokenHash;

    @Column("expired_at")
    private Instant expiredAt;

    @Column("create_at")
    private Instant createAt;

    private boolean revoked;
}
