package com.example.__WebFlux.domain.refreshToken.ports;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;

import reactor.core.publisher.Mono;

public interface RefreshTokenDomainRepository {

    Mono<RefreshTokenModel> findByTokenHash(String tokenHash);

    Mono<RefreshTokenModel> findById(String id);

    Mono<RefreshTokenModel> findByUserIdAndNotRevoked(String userId);

    Mono<RefreshTokenModel> save(RefreshTokenModel token);
}
