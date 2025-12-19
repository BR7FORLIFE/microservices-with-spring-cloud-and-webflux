package com.example.__WebFlux.application.auth.usecases;

import com.example.__WebFlux.application.auth.command.RegisterUserCommand;
import com.example.__WebFlux.application.auth.command.RegisterUserCommandResult;

import reactor.core.publisher.Mono;

public interface AuthUseCase {
    Mono<RegisterUserCommandResult> execute(RegisterUserCommand cmd);
}
