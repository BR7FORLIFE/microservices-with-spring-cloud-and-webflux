package com.example.webflux.infrastructure.security.adapters;

import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.webflux.application.auth.model.AuthenticatedUser;
import com.example.webflux.application.auth.ports.UserJwtPort;
import com.example.webflux.domain.auth.models.UserModelDomain;
import com.example.webflux.infrastructure.security.CustomUserDetails;
import com.example.webflux.infrastructure.security.jwt.JwtService;

import reactor.core.publisher.Mono;

@Component
public class UserJwtAdapter implements UserJwtPort {

    private final JwtService jwtService;

    public UserJwtAdapter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Mono<String> generateAccessToken(AuthenticatedUser user) {
        UserDetails details = new CustomUserDetails(
                new UserModelDomain(
                        user.userId(),
                        user.username(),
                        null,
                        null,
                        user.password(),
                        Set.copyOf(user.rols())));

        return jwtService.generateAccessToken(details);
    }

}
