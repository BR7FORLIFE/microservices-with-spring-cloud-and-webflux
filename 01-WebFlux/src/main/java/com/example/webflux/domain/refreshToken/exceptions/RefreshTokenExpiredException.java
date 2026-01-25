package com.example.webflux.domain.refreshToken.exceptions;

public class RefreshTokenExpiredException extends RuntimeException {

    public RefreshTokenExpiredException() {
        super("Refresh token has expired!");
    }
}
