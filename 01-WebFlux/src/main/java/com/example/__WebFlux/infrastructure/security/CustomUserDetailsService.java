package com.example.__WebFlux.infrastructure.security;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public class CustomUserDetailsService implements ReactiveUserDetailsService{

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
