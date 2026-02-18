package com.example.webflux.infrastructure.emailVerificationToken.mapper;

import com.example.webflux.domain.emailVerificationToken.models.EmailVerificationTokenModel;
import com.example.webflux.infrastructure.emailVerificationToken.persistence.EmailVerificationTokenEntity;

public class EmailVerificationTokenMapper {

    public static EmailVerificationTokenModel toDomain(EmailVerificationTokenEntity entity) {
        return EmailVerificationTokenModel.createNew(entity.getUserId(), entity.getTokenHash(), entity.getExpiredAt(),
                entity.getConsumedAt(), entity.getCreateAt());
    }

    public static EmailVerificationTokenEntity toEntity(EmailVerificationTokenModel domain) {
        EmailVerificationTokenEntity entity = new EmailVerificationTokenEntity();

        entity.setUserId(domain.getUserId());
        entity.setTokenHash(entity.getTokenHash());
        entity.setExpiredAt(entity.getExpiredAt());
        entity.setCreateAt(entity.getCreateAt());
        entity.setConsumedAt(domain.getConsumedAt());

        return entity;
    }
}
