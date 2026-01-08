package com.example.__WebFlux.domain.refreshToken.ports;

import java.util.UUID;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RefreshTokenDomainRepositoryPort {

    Mono<Void> revokeAllByUserId(UUID userId);

    Mono<RefreshTokenModel> findByTokenHash(String tokenHash);

    Mono<RefreshTokenModel> findById(UUID id);

    Flux<RefreshTokenModel> findByUserIdAndNotRevoked(UUID userId);

    Mono<RefreshTokenModel> save(RefreshTokenModel token);
}
