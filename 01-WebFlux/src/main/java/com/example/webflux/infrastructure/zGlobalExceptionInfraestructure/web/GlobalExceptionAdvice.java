package com.example.webflux.infrastructure.zGlobalExceptionInfraestructure.web;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;

import com.example.webflux.application.auth.exceptions.AuthStatusEmailVerified;
import com.example.webflux.application.auth.exceptions.IncorrectPasswordException;
import com.example.webflux.application.auth.exceptions.UserAlreadyRegisterException;
import com.example.webflux.application.auth.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    // === EXCEPTIONES DE AUTENTICACION ===

    @ExceptionHandler(AuthStatusEmailVerified.class)
    public ResponseEntity<ApiError> handleEmailNotVerified(
            AuthStatusEmailVerified ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(), exchange);
    }

    // Exepciones cuando el usuario esta registrado en la plataforma
    @ExceptionHandler(UserAlreadyRegisterException.class)
    public ResponseEntity<ApiError> handleRegisterAuth(
            UserAlreadyRegisterException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), exchange);
    }

    // Excepciones cuando no se encuentra un usuario
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(
            UserNotFoundException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.NOT_FOUND, ex.getMessage(), exchange);
    }

    // Excepciones cuando la contraseña es incorrecta
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<ApiError> handleIncorrectPassword(
            IncorrectPasswordException ex,
            ServerWebExchange exchange) {
        return buildError(HttpStatus.FORBIDDEN, ex.getMessage(), exchange);
    }

    // === HTTP ====

    // Excepciones cuando se manda en el cuerpo de la peticion un campo no permitido
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ApiError> handleMultipleFields(
            ServerWebInputException e,
            ServerWebExchange exchange) {
        if (e.getCause() instanceof UnrecognizedPropertyException ex) {
            return buildError(HttpStatus.TOO_MANY_REQUESTS, "Propiedad no permitida: " + ex.getPropertyName(),
                    exchange);
        }

        return buildError(HttpStatus.TOO_MANY_REQUESTS, "Datos invalidos!", exchange);
    }

    // helper para los codigos de estados
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
