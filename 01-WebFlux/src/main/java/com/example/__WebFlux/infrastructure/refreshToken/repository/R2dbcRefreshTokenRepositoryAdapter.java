package com.example.__WebFlux.infrastructure.refreshToken.repository;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;
import com.example.__WebFlux.domain.refreshToken.ports.RefreshTokenDomainRepositoryPort;

import reactor.core.publisher.Mono;

public class R2dbcRefreshTokenRepositoryAdapter implements RefreshTokenDomainRepositoryPort {

    @Override
    public Mono<RefreshTokenModel> findById(String id) {
        return null;
    }

    @Override
    public Mono<RefreshTokenModel> findByTokenHash(String tokenHash) {
        return null;
    }

    @Override
    public Mono<RefreshTokenModel> findByUserIdAndNotRevoked(String userId) {
        return null;
    }

    @Override
    public Mono<RefreshTokenModel> save(RefreshTokenModel token) {
        return null;
    }
}
