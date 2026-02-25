package com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import com.example.webflux.application.auth.exceptions.IncorrectPasswordException;
import com.example.webflux.application.auth.exceptions.UserAlreadyRegisterException;
import com.example.webflux.application.auth.exceptions.UserNotFoundException;
import com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.helpers.ApiError;
import com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.helpers.StaticError;

@RestControllerAdvice
public class AuthGlobalAdviceExceptions {

    // === EXCEPTIONES DE AUTENTICACION ===

    // Exepciones cuando el usuario esta registrado en la plataforma
    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ApiError> handleRegisterAuth(
            UserAlreadyRegisterException ex,
            ServerWebExchange exchange) {
        return StaticError.buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), exchange);
    }

    // Excepciones cuando no se encuentra un usuario
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException ex,
            ServerWebExchange exchange) {
        return StaticError.buildError(HttpStatus.NOT_FOUND, ex.getMessage(), exchange);
    }

    // Excepciones cuando la contraseña es incorrecta
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> handleIncorrectPassword(
            IncorrectPasswordException ex,
            ServerWebExchange exchange) {
        return StaticError.buildError(HttpStatus.FORBIDDEN, ex.getMessage(), exchange);
    }
}
