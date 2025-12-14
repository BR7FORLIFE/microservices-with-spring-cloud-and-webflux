package com.example.__WebFlux.infrastructure.security.repository;

import com.example.__WebFlux.domain.user.models.UserModel;
import com.example.__WebFlux.domain.user.ports.UserDomainRepositoryPort;
import com.example.__WebFlux.infrastructure.security.repository.postgres.SpringDataUserRepository;

import reactor.core.publisher.Mono;

public class R2dbcUserRepositoryAdapter  implements UserDomainRepositoryPort {

    private final SpringDataUserRepository userRepository;

    public R2dbcUserRepositoryAdapter(SpringDataUserRepository springDataUserRepository){
        this.userRepository = springDataUserRepository;
    }

    @Override
    public Mono<UserModel> findByUsername(String username) {
        return null;
    }

    @Override
    public Mono<UserModel> findByEmail(String email) {
        return null;
    }
    
}
