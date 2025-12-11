package com.example.__WebFlux.domain.refreshToken.ports;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RefreshTokenDomainRepositoryPort {

    Mono<RefreshTokenModel> findByTokenHash(String tokenHash);

    Mono<RefreshTokenModel> findById(String id);

    Flux<RefreshTokenModel> findByUserIdAndNotRevoked(String userId);

    Mono<RefreshTokenModel> save(RefreshTokenModel token);
}
