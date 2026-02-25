package com.example.webflux.application.emailVerificationToken.exceptions;

import com.example.webflux.application.zGlobalApplicationExceptions.ApplicationException;

public class InvalidTokenException extends ApplicationException {

    public InvalidTokenException() {
        super("Token de verificacion de email invalido o no encontrado!");
    }
}
