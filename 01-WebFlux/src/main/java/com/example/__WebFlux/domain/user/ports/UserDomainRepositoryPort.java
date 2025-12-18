package com.example.__WebFlux.domain.user.ports;

import com.example.__WebFlux.domain.user.models.UserModelDomain;

import reactor.core.publisher.Mono;

public interface UserDomainRepositoryPort {
    Mono<UserModelDomain> findByUsername(String username);
    Mono<UserModelDomain> findByEmail(String email);
}
