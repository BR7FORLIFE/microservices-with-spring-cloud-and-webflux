package com.example.webflux.application.refreshToken.orchestrator;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.HexFormat;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.webflux.application.refreshToken.exceptions.ValidateAndRotateException;
import com.example.webflux.application.refreshToken.usecases.RefreshTokenUseCase;
import com.example.webflux.domain.refreshToken.models.RefreshTokenModel;
import com.example.webflux.domain.refreshToken.ports.RefreshTokenDomainRepositoryPort;
import com.example.webflux.domain.refreshToken.services.RefreshTokenDomainService;
import com.nimbusds.jose.util.StandardCharset;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.function.Tuples;

@Service
public class RefreshTokenUseCaseImp implements RefreshTokenUseCase {

    private final RefreshTokenDomainRepositoryPort repository; // <-- puerto del dominio de la feature RefreshToken
    private final Duration ttlDuration; // <-- representa una cantidad de tiempo ej: 5 Min, 30 Days, 90 seconds
    private final SecureRandom secureRandom = new SecureRandom(); // <- generador de numeros aleatorios con gran
                                                                  // entropia

    public RefreshTokenUseCaseImp(RefreshTokenDomainRepositoryPort repo,
            @Value("${refresh-token.ttl}") Duration duration) {
        this.repository = repo;
        this.ttlDuration = duration;
    }

    // creamos el refreshToken
    /**
     * Nos basamos de la entropia para generar el RawToken
     */
    @Override
    public Mono<String> createRefreshToken(UUID userId) {
        return repository.revokeAllByUserId(userId)
                .then(Mono.fromCallable(() -> {
                    UUID jti = UUID.randomUUID(); // <-- id unico de refreshToken Model
                    String raw = this.randomBase64(32); // <-- Raw que devolvemos en el mono Base64 con alta entropia
                    String hash = this.sha256(raw); // <-- Obligatorio hasearlo para seguridad por si roban la DB
                    Instant now = Instant.now();
                    Instant expiredAt = now.plus(ttlDuration);
                    RefreshTokenModel refreshtokenModel = RefreshTokenModel.create(jti, userId, hash,
                            expiredAt, false, expiredAt);
                    return Tuples.of(refreshtokenModel, raw);
                }).flatMap(tuple -> {
                    return repository.save(tuple.getT1()).thenReturn(tuple.getT2());
                }).subscribeOn(Schedulers.boundedElastic()));
    }

    // recibe el raw token, busca por el hash, valida y rota el token
    @Override
    public Mono<String> validateAndRotate(String rawToken) {
        String hash = this.sha256(rawToken);
        return repository.findByTokenHash(hash)
                .switchIfEmpty(Mono.error(new ValidateAndRotateException()))
                .flatMap(rt -> {
                    RefreshTokenDomainService.validateToken(rt, Instant.now()); // <-- el dominio valida si es correcto
                                                                                // o no el refresh token

                    RefreshTokenModel revoked = RefreshTokenDomainService.revoke(rt); // <- el dominio revoca la copia

                    return repository.save(revoked).then(this.createRefreshToken(rt.getUserId()));
                });
    }

    public Mono<Void> revoke(String rawToken) {
        String hash = this.sha256(rawToken);
        return repository.findByTokenHash(hash)
                .flatMap(rt -> repository.save(rt.revoke())).then();
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
