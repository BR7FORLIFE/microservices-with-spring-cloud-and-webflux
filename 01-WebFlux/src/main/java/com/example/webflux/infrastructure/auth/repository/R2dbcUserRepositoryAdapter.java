package com.example.webflux.infrastructure.auth.repository;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.webflux.domain.auth.models.UserModelDomain;
import com.example.webflux.domain.auth.ports.UserDomainRepositoryPort;
import com.example.webflux.infrastructure.auth.mapper.UserMapper;
import com.example.webflux.infrastructure.auth.persistence.UserRolEntity;
import com.example.webflux.infrastructure.auth.repository.postgres.SpringDataUserRepository;
import com.example.webflux.infrastructure.auth.repository.postgres.SpringDataUserRolRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class R2dbcUserRepositoryAdapter implements UserDomainRepositoryPort {

    private final SpringDataUserRepository userRepository;
    private final SpringDataUserRolRepository userRolRepository;

    public R2dbcUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository,
            SpringDataUserRolRepository userRolRepository) {
        this.userRepository = springDataUserRepository;
        this.userRolRepository = userRolRepository;
    }

    @Override
    public Mono<UserModelDomain> findByUserId(UUID id) {
        return userRepository.findById(id)
                .flatMap(user -> userRolRepository.findByUserId(user.getId())
                        .map(UserRolEntity::getUserRol)
                        .collect(Collectors.toSet())
                        .map(rols -> new UserModelDomain(user.getId(), user.getUsername(), user.getEmail(),
                                user.getPasswordHash(), rols)));
    }

    @Override
    public Mono<UserModelDomain> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .flatMap(user -> userRolRepository.findByUserId(user.getId())
                        .map(UserRolEntity::getUserRol)
                        .collect(Collectors.toSet())
                        .map(rols -> new UserModelDomain(user.getId(), user.getUsername(), user.getEmail(),
                                user.getPasswordHash(), rols)));
    }

    @Override
    public Mono<UserModelDomain> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public Mono<UserModelDomain> save(UserModelDomain userModelDomain) {
        return userRepository.save(UserMapper.toEntity(userModelDomain))
                .flatMap(userSaved -> Flux.fromIterable(userModelDomain.getRols())
                        .map(role -> new UserRolEntity(userSaved.getId(), role))
                        .flatMap(userRolRepository::save)
                        .then(Mono.just(userSaved)))
                .map(UserMapper::toDomain);
    }
}
