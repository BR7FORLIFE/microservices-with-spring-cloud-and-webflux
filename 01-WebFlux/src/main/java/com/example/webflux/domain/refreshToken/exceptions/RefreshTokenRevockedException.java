package com.example.webflux.domain.refreshToken.exceptions;

public class RefreshTokenRevockedException extends RuntimeException {
    public RefreshTokenRevockedException() {
        super("Refresh token has revocked!");
    }
}
