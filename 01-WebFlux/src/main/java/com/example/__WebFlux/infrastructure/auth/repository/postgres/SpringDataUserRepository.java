package com.example.__WebFlux.infrastructure.auth.repository.postgres;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.__WebFlux.infrastructure.auth.persistence.UserModelEntity;

import reactor.core.publisher.Mono;

public interface SpringDataUserRepository extends ReactiveCrudRepository<UserModelEntity, String>{
    Mono<UserModelEntity> findByUsername(String username);
    Mono<UserModelEntity> findByEmail(String email);
}
