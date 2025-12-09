package com.example.__WebFlux.application.refreshToken.services;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

import com.example.__WebFlux.application.refreshToken.usecases.RefreshTokenUseCase;
import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;
import com.example.__WebFlux.domain.refreshToken.ports.RefreshTokenDomainRepositoryPort;
import com.nimbusds.jose.util.StandardCharset;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuples;

public class RefreshTokenUseCaseImp implements RefreshTokenUseCase {

    private final RefreshTokenDomainRepositoryPort repository; // <-- puerto del dominio de la feature RefreshToken
    private final Duration ttlDuration; // <-- representa una cantidad de tiempo ej: 5 Min, 30 Days, 90 seconds
    private final SecureRandom secureRandom = new SecureRandom(); // <- generador de numeros aleatorios con gran
                                                                  // entropia

    public RefreshTokenUseCaseImp(RefreshTokenDomainRepositoryPort repo, Duration duration) {
        this.repository = repo;
        this.ttlDuration = duration;
    }

    // creamos el refreshToken
    /**
     * Nos basamos de la entropia para generar el RawToken
     */
    @Override
    public Mono<String> createRefreshToken(String userId) {
        return Mono.fromCallable(() -> {
            String jti = UUID.randomUUID().toString(); // <-- id unico de refreshToken Model
            String raw = randomBase64(32); // <-- Raw que devolvemos en el mono Base64 con alta entropia
            String hash = sha256(raw); // <-- Obligatorio hasearlo para seguridad por si roban la DB
            Instant now = Instant.now();
            Instant expiredAt = now.plus(ttlDuration);
            RefreshTokenModel token = new RefreshTokenModel(jti, userId, hash, expiredAt, false, expiredAt);
            return Tuples.of(token, raw);
        }).flatMap(tuple -> {
            return repository.save(tuple.getT1()).thenReturn(tuple.getT2());
        }).subscribeOn(Schedulers.boundedElastic());
    }

    // recibe el raw token, busca por el hash, valida y rota el token
    @Override
    public Mono<String> validateAndRotate(String rawToken) {
        String hash = sha256(rawToken);
        return repository.findByTokenHash(hash)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid refresh token")))
                .flatMap(rt -> {
                    if (rt.isRevoked() || rt.isExpired(Instant.now())) {
                        return Mono.error(new IllegalArgumentException("Refresh token invalid"));
                    }

                    RefreshTokenModel revoked = rt.revokedCopy();
                    return repository.save(revoked).then(createRefreshToken(rt.getUserId()));
                });
    }

    public Mono<Void> revoke(String rawToken) {
        String hash = sha256(rawToken);
        return repository.findByTokenHash(hash)
                .flatMap(rt -> repository.save(rt.revokedCopy())).then();
    }

    private String randomBase64(int bytes) {
        byte[] b = new byte[bytes];

        // asigna a cada bit de b un bit con entropia para usar
        secureRandom.nextBytes(b);
        /**
         * Convierte a base64 especializado para url gracias a UrlEncoder
         * 
         * -> withoutPadding() -> es para eliminar el tipico ' = ' cuando es multiplo de
         * 3
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
