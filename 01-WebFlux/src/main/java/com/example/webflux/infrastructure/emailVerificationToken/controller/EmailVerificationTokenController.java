package com.example.webflux.infrastructure.emailVerificationToken.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.application.emailVerificationToken.commands.VerifyEmailCommand;
import com.example.webflux.application.emailVerificationToken.dto.response.EmailVerifyResponseDto;
import com.example.webflux.application.emailVerificationToken.usecases.EmailVerifiedTokenUseCase;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/email")
public class EmailVerificationTokenController {

    private final EmailVerifiedTokenUseCase emailVerifiedTokenUseCase;

    public EmailVerificationTokenController(EmailVerifiedTokenUseCase emailVerifiedTokenUseCase) {
        this.emailVerifiedTokenUseCase = emailVerifiedTokenUseCase;
    }

    @GetMapping("/verify?token={rawToken}")
    public Mono<ResponseEntity<EmailVerifyResponseDto>> verifiedEmailToken(@PathVariable String rawToken) {
        VerifyEmailCommand cmd = new VerifyEmailCommand(rawToken);

        return emailVerifiedTokenUseCase.verifyEmail(cmd)
                .map(result -> ResponseEntity.ok().body(new EmailVerifyResponseDto(result.message())));
    }
}
