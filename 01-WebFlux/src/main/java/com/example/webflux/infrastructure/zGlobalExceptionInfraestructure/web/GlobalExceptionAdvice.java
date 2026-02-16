package com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import com.example.webflux.application.auth.exceptions.IncorrectPasswordException;
import com.example.webflux.application.auth.exceptions.UserAlreadyRegisterException;
import com.example.webflux.application.auth.exceptions.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ApiError> handleRegisterAuth(
            UserAlreadyRegisterException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), exchange);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), exchange);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> handleIncorrectPassword(
            IncorrectPasswordException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.FORBIDDEN, ex.getMessage(), exchange);
    }

    private ResponseEntity<ApiError> buildError(
            HttpStatus status,
            String message,
            ServerWebExchange exchange) {

        ApiError error = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                exchange.getRequest().getPath().value());

        return ResponseEntity.status(status).body(error);
    }

}
