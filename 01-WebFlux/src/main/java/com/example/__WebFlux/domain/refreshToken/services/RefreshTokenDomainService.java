package com.example.__WebFlux.domain.refreshToken.services;

import java.time.Instant;

import com.example.__WebFlux.domain.refreshToken.exceptions.RefreshTokenExpiredException;
import com.example.__WebFlux.domain.refreshToken.exceptions.RefreshTokenRevockedException;
import com.example.__WebFlux.domain.refreshToken.models.RefreshTokenModel;

public class RefreshTokenDomainService {

    public void validateToken(RefreshTokenModel token, Instant now) {
        if (token.isRevoked()) {
            throw new RefreshTokenRevockedException();
        }

        if (token.isExpired(now)) {
            throw new RefreshTokenExpiredException();
        }
    }

    public RefreshTokenModel revoke(RefreshTokenModel token){
        return token.revokedCopy();
    } 
}
