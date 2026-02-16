package com.example.webflux.infrastructure.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.webflux.infrastructure.auth.repository.R2dbcUserRepositoryAdapter;
import com.example.webflux.infrastructure.security.ports.CustomReactiveUserDetailsService;

import reactor.core.publisher.Mono;

@Service
public class CustomReactiveUserDetailsServiceImp implements CustomReactiveUserDetailsService {

    private final R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter;

    public CustomReactiveUserDetailsServiceImp(R2dbcUserRepositoryAdapter r2dbcUserRepositoryAdapter) {
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
