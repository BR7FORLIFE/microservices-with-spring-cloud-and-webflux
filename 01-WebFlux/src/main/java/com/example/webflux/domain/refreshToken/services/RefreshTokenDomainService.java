package com.example.webflux.domain.refreshToken.services;

import java.time.Instant;

import com.example.webflux.domain.refreshToken.models.RefreshTokenModel;

public class RefreshTokenDomainService {

    public static boolean validateToken(RefreshTokenModel token, Instant now) {
        if (token.isRevoked()) {
            return true;
        }

        if (token.isExpired(now)) {
            return true;
        }
        return false;
    }

    public static RefreshTokenModel revoke(RefreshTokenModel token) {
        return token.revokedCopy();
    }
}
