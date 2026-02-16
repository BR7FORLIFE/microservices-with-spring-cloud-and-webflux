package com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.web;

import java.time.LocalDateTime;

public record ApiError(
        LocalDateTime localDateTime,
        int status,
        String error,
        String message,
        String path) {

}
