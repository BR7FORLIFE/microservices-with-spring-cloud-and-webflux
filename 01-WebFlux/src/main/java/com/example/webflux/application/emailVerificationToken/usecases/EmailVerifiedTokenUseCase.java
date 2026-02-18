package com.example.webflux.application.emailVerificationToken.usecases;

import com.example.webflux.application.emailVerificationToken.commands.VerifyEmailCommand;
import com.example.webflux.application.emailVerificationToken.commands.VerifyEmailCommandResult;

import reactor.core.publisher.Mono;

public interface EmailVerifiedTokenUseCase {
    Mono<VerifyEmailCommandResult> verifyEmail(VerifyEmailCommand cmd);
}
