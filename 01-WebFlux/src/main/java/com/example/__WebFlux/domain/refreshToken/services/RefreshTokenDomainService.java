package com.example.__WebFlux.domain.refreshToken.services;

import java.time.Instant;

import com.example.__WebFlux.domain.refreshToken.exceptions.RefreshTokenExpiredException;
import com.example.__WebFlux.domain.refreshToken.exceptions.RefreshTokenRevockedException;
import com.example.__WebFlux.domain.refreshToken.models.RefreshToken;

public class RefreshTokenDomainService {

    public void validateToken(RefreshToken token, Instant now) {
        if (token.isRevoked()) {
            throw new RefreshTokenRevockedException();
        }

        if (token.isExpired(now)) {
            throw new RefreshTokenExpiredException();
        }
    }

    public RefreshToken revoke(RefreshToken token){
        return token.markRevoked();
    } 
}
