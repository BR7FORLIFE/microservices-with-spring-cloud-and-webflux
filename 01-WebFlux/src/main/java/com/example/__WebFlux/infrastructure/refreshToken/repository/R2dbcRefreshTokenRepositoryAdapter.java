package com.example.__WebFlux.infrastructure.refreshToken.repository;

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

    public R2dbcRefreshTokenRepositoryAdapter(SpringDataRefreshTokenRepository repository){
        this.refreshTokenRepository = repository;
    }

    @Override
    public Mono<RefreshTokenModel> findById(String id) {
        return refreshTokenRepository
                .findById(id)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Mono<RefreshTokenModel> findByTokenHash(String tokenHash) {
        return refreshTokenRepository
                .findByTokenHash(tokenHash)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Flux<RefreshTokenModel> findByUserIdAndNotRevoked(String userId) {
        return refreshTokenRepository
                .findByUserIdAndRevokedIsFalse(userId)
                .map(RefreshTokenMapper::toDomain);
    }

    @Override
    public Mono<RefreshTokenModel> save(RefreshTokenModel tokenModel) {
        return refreshTokenRepository
                .save(RefreshTokenMapper.toEntity(tokenModel))
                .map(RefreshTokenMapper::toDomain);
    }

}
