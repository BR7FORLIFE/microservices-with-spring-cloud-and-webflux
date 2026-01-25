package com.example.webflux.infrastructure.refreshToken.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.webflux.domain.refreshToken.models.RefreshTokenModel;
import com.example.webflux.domain.refreshToken.ports.RefreshTokenDomainRepositoryPort;
import com.example.webflux.infrastructure.refreshToken.mapper.RefreshTokenMapper;
import com.example.webflux.infrastructure.refreshToken.repository.postgres.SpringDataRefreshTokenRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
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
