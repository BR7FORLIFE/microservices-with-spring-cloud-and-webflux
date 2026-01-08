package com.example.__WebFlux.infrastructure.refreshToken.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;
import com.example.__WebFlux.domain.refreshToken.ports.RefreshTokenDomainRepositoryPort;
import com.example.__WebFlux.infrastructure.refreshToken.mapper.RefreshTokenMapper;
import com.example.__WebFlux.infrastructure.refreshToken.repository.postgres.SpringDataRefreshTokenRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class R2dbcRefreshTokenRepositoryAdapter implements RefreshTokenDomainRepositoryPort {

    private final SpringDataRefreshTokenRepository refreshTokenRepository;

    public R2dbcRefreshTokenRepositoryAdapter(SpringDataRefreshTokenRepository repository) {
        this.refreshTokenRepository = repository;
    }

    @Override
    public Mono<RefreshTokenModel> findById(UUID id) {
        return this.refreshTokenRepository
                .findById(id)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Mono<RefreshTokenModel> findByTokenHash(String tokenHash) {
        return this.refreshTokenRepository
                .findByTokenHash(tokenHash)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Flux<RefreshTokenModel> findByUserIdAndNotRevoked(UUID userId) {
        return this.refreshTokenRepository
                .findByUserIdAndRevokedIsFalse(userId)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Mono<RefreshTokenModel> save(RefreshTokenModel tokenModel) {
        return this.refreshTokenRepository
                .save(RefreshTokenMapper.toEntity(tokenModel))
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Mono<Void> revokeAllByUserId(UUID userId) {
        return this.refreshTokenRepository.revokeAllByUserId(userId);
    }
}
