package com.example.__WebFlux.application.auth.usecases;

import java.util.UUID;

import com.example.__WebFlux.application.auth.command.RegisterUserCommand;
import com.example.__WebFlux.application.auth.command.RegisterUserCommandResult;
import com.example.__WebFlux.application.auth.command.VerifiedUserCommandResult;

import reactor.core.publisher.Mono;

public interface AuthUseCase {
    Mono<RegisterUserCommandResult> execute(RegisterUserCommand cmd);
    Mono<VerifiedUserCommandResult> verifyUser(UUID userId);
}
