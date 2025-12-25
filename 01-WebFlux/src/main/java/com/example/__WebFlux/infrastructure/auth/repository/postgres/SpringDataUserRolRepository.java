package com.example.__WebFlux.infrastructure.auth.repository.postgres;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.example.__WebFlux.infrastructure.auth.persistence.UserRolEntity;

import reactor.core.publisher.Flux;

public interface SpringDataUserRolRepository extends CrudRepository<UserRolEntity, Long> {
    Flux<UserRolEntity> findByUserId(UUID user_id);
}
