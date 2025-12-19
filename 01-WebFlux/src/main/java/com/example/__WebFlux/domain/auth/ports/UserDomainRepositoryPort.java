package com.example.__WebFlux.domain.auth.ports;

import com.example.__WebFlux.domain.auth.models.UserModelDomain;

import reactor.core.publisher.Mono;

public interface UserDomainRepositoryPort {
    Mono<UserModelDomain> findByUsername(String username);
    Mono<UserModelDomain> findByEmail(String email);
    Mono<Boolean> existsByUsername(String username);
    Mono<UserModelDomain> save(UserModelDomain userModelDomain);
}
