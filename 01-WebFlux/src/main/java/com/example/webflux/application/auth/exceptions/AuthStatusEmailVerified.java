package com.example.webflux.application.auth.exceptions;

import com.example.webflux.application.zGlobalApplicationExceptions.ApplicationException;

public class AuthStatusEmailVerified extends ApplicationException {

    public AuthStatusEmailVerified() {
        super("Email is not verified");
    }
}
