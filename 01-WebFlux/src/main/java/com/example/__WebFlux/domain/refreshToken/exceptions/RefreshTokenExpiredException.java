package com.example.__WebFlux.domain.refreshToken.exceptions;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException() {
        super("Refresh token has expired!");
    }
}
