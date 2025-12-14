package com.example.__WebFlux.domain.user.ports;

import com.example.__WebFlux.domain.user.models.UserModel;

import reactor.core.publisher.Mono;

public interface UserDomainRepositoryPort {
    Mono<UserModel> findByUsername(String username);
    Mono<UserModel> findByEmail(String email);
}
