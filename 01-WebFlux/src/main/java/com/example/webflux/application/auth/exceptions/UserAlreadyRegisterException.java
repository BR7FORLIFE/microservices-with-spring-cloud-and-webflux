package com.example.webflux.application.auth.exceptions;

import com.example.webflux.application.zGlobalApplicationExceptions.ApplicationException;

public class UserAlreadyRegisterException extends ApplicationException {

    public UserAlreadyRegisterException() {
        super("El usuario se encuentra registrado!");
    }

    
}
