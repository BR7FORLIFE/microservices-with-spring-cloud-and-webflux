package com.example.__WebFlux.application.refreshToken.usecases;

import reactor.core.publisher.Mono;

public interface RefreshTokenUseCase {
    Mono<String> createRefreshToken(String userId);

    Mono<String> validateAndRotate(String rawToken);
}
