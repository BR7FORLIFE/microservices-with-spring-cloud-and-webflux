package com.example.__WebFlux.infrastructure.refreshToken.mapper;

import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;
import com.example.__WebFlux.infrastructure.refreshToken.persistence.RefreshTokenEntity;

public class RefreshTokenMapper {

    public static RefreshTokenModel toDomain(RefreshTokenEntity refreshTokenEntity) {
        return new RefreshTokenModel(refreshTokenEntity.getId(), refreshTokenEntity.getUserId(),
                refreshTokenEntity.getTokenHash(), refreshTokenEntity.getExpiredAt(), refreshTokenEntity.isRevoked(),
                refreshTokenEntity.getCreateAt());
    }

    public static RefreshTokenEntity toEntity(RefreshTokenModel refreshTokenModel) {
        RefreshTokenEntity entity = new RefreshTokenEntity();
        entity.setId(refreshTokenModel.getId());
        entity.setUserId(refreshTokenModel.getUserId());
        entity.setTokenHash(refreshTokenModel.getTokenHash());
        entity.setExpiredAt(refreshTokenModel.getExpiresAt());
        entity.setRevoked(refreshTokenModel.isRevoked());
        entity.setCreateAt(refreshTokenModel.getCreateAt());

        return entity;
    }
}
