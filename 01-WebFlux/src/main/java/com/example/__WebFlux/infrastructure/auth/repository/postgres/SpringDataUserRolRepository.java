package com.example.__WebFlux.infrastructure.auth.repository.postgres;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.__WebFlux.infrastructure.auth.persistence.UserRolEntity;

import reactor.core.publisher.Flux;

public interface SpringDataUserRolRepository extends ReactiveCrudRepository<UserRolEntity, Long> {
    Flux<UserRolEntity> findByUserId(UUID userId);
}
