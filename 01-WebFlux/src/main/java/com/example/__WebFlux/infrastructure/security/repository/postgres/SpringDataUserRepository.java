package com.example.__WebFlux.infrastructure.security.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.__WebFlux.infrastructure.security.persistence.UserModel;

import reactor.core.publisher.Mono;

public interface SpringDataUserRepository extends ReactiveCrudRepository<UserModel, String>{
    Mono<UserModel> findByUsername(String username);
    Mono<UserModel> findByEmail(String email);
}
