package com.example.__WebFlux.infrastructure.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.__WebFlux.infrastructure.security.repository.R2dbcUserRepositoryAdapter;

import reactor.core.publisher.Mono;

public class CustomReactiveUserDetailsService implements ReactiveUserDetailsService {

    private final R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter;

    public CustomReactiveUserDetailsService(R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter) {
        this.r2dbcUserRepositoryAdapter = r2dbcUserRepositoryAdapter;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return r2dbcUserRepositoryAdapter.findByUsername(username)
                .map(CustomUserDetails::new);
    }

    public Mono<UserDetails> findByEmail(String email) {
        return r2dbcUserRepositoryAdapter.findByEmail(email)
                .map(CustomUserDetails::new);
    }
}
