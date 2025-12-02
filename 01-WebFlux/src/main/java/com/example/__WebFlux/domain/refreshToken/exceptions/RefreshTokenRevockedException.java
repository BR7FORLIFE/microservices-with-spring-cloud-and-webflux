package com.example.__WebFlux.domain.refreshToken.exceptions;

public class RefreshTokenRevockedException extends RuntimeException {
    public RefreshTokenRevockedException() {
        super("Refresh token has revocked!");
    }
}
