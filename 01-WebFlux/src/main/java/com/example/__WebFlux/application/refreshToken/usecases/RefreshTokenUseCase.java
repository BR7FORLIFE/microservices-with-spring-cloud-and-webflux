package com.example.__WebFlux.application.refreshToken.usecases;

import java.util.UUID;

import reactor.core.publisher.Mono;

public interface RefreshTokenUseCase {
    Mono<String> createRefreshToken(UUID userId);

    Mono<String> validateAndRotate(String rawToken);
}
