package com.example.__WebFlux.infrastructure.security.jwt;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class JwtService {

    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;
    private String issuer = "";
    private Integer accessTokenSeconds = 60 * 60 * 60;

    public JwtService(KeyPair keyPair) {
        this.publicKey = (RSAPublicKey) keyPair.getPublic();
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public Mono<String> generateAccessToken() {
        return Mono.just(null);
    }
}
