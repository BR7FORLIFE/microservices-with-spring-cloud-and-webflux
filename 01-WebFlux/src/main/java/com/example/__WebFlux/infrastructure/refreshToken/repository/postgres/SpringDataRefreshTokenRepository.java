package com.example.__WebFlux.infrastructure.refreshToken.repository.postgres;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.__WebFlux.infrastructure.refreshToken.persistence.RefreshTokenEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpringDataRefreshTokenRepository extends ReactiveCrudRepository<RefreshTokenEntity, String> {

    @Query("SELECT * FROM refresh_token WHERE token_hash = :token")
    Mono<RefreshTokenEntity> findByTokenHash(String token);

   Flux<RefreshTokenEntity> findByUserIdAndRevokedIsFalse(String userId);
}
