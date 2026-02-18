package com.example.webflux.application.emailVerificationToken.orchestator;

import org.springframework.stereotype.Service;

import com.example.webflux.application.emailVerificationToken.commands.VerifyEmailCommand;
import com.example.webflux.application.emailVerificationToken.commands.VerifyEmailCommandResult;
import com.example.webflux.application.emailVerificationToken.usecases.EmailVerifiedTokenUseCase;

import reactor.core.publisher.Mono;

@Service
public class EmailVerifyTokenUseCaseImp implements EmailVerifiedTokenUseCase {
    @Override
    public Mono<VerifyEmailCommandResult> verifyEmail(VerifyEmailCommand cmd) {

        return null;
    }
}
