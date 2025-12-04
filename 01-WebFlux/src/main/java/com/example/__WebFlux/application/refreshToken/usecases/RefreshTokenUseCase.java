package com.example.__WebFlux.application.refreshToken.usecases;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

import com.example.__WebFlux.domain.refreshToken.models.RefreshToken;
import com.example.__WebFlux.domain.refreshToken.ports.RefreshTokenDomainRepository;
import com.nimbusds.jose.util.StandardCharset;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class RefreshTokenUseCase {

    private final RefreshTokenDomainRepository repository;
    private final Duration ttlDuration;
    private final SecureRandom secureRandom = new SecureRandom();

    public RefreshTokenUseCase(RefreshTokenDomainRepository repo, Duration duration) {
        this.repository = repo;
        this.ttlDuration = duration;
    }

    // creamos el refreshToken
    /**
     * Nos basamos de la entropia para generar el RawToken
     */

    public Mono<String> createRefreshToken(String userId) {
        return Mono.fromCallable(() -> {
            String jti = UUID.randomUUID().toString();
            String raw = randomBase64(32);
            String hash = sha256(raw);
            Instant now = Instant.now();
            Instant expiredAt = now.plus(ttlDuration);
            RefreshToken token = new RefreshToken(jti, userId, hash, expiredAt, false, expiredAt);
            return new Object[] { token, raw };
        }).flatMap(arr -> {
            RefreshToken token = (RefreshToken) arr[0];
            String raw = (String) arr[1];
            return repository.save(token).thenReturn(raw);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private String randomBase64(int bytes) {
        byte[] b = new byte[bytes];

        // asigna a cada bit de b un bit con entropia para usar
        secureRandom.nextBytes(b);
        /**
         * Convierte a base64 especializado para url gracias a UrlEncoder
         * 
         * -> withoutPadding() -> es para eliminar el tipico = cuando es multiplo de 3
         */
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }

    private String sha256(String value) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] digest = sha.digest(value.getBytes(StandardCharset.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
