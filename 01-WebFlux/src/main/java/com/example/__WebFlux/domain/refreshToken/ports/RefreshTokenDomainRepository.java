package com.example.__WebFlux.domain.refreshToken.ports;

import com.example.__WebFlux.domain.refreshToken.models.RefreshToken;

import reactor.core.publisher.Mono;

public interface RefreshTokenDomainRepository {

    Mono<RefreshToken> findByTokenHash(String tokenHash);

    Mono<RefreshToken> findById(String id);

    Mono<RefreshToken> findByUserIdAndNotRevoked(String userId);

    Mono<RefreshToken> save(RefreshToken token);
}
