package com.example.__WebFlux.infrastructure.auth.repository;

import org.springframework.stereotype.Repository;

import com.example.__WebFlux.domain.auth.models.UserModelDomain;
import com.example.__WebFlux.domain.auth.ports.UserDomainRepositoryPort;
import com.example.__WebFlux.infrastructure.auth.mapper.UserMapper;
import com.example.__WebFlux.infrastructure.auth.repository.postgres.SpringDataUserRepository;

import reactor.core.publisher.Mono;

@Repository
public class R2dbcUserRepositoryAdapter implements UserDomainRepositoryPort {

    private final SpringDataUserRepository userRepository;

    public R2dbcUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository) {
        this.userRepository = springDataUserRepository;
    }

    @Override
    public Mono<UserModelDomain> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::toDomain);
    }

    @Override
    public Mono<UserModelDomain> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Mono<Boolean> existsByUsername(String username) {
        return null;
    }

    @Override
    public Mono<UserModelDomain> save(UserModelDomain userModelDomain) {
        return null;
    }

}
