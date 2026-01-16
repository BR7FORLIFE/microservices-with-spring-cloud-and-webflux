package com.example.__WebFlux.application.auth.orchestator;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.__WebFlux.application.auth.command.RegisterUserCommand;
import com.example.__WebFlux.application.auth.command.RegisterUserCommandResult;
import com.example.__WebFlux.application.auth.command.VerifiedUserCommandResult;
import com.example.__WebFlux.application.auth.usecases.AuthUseCase;
import com.example.__WebFlux.domain.auth.models.UserModelDomain;
import com.example.__WebFlux.domain.auth.ports.UserDomainRepositoryPort;

import reactor.core.publisher.Mono;

@Service
public class AuthUseCaseImp implements AuthUseCase {

    private final UserDomainRepositoryPort userPort;
    private final PasswordEncoder passwordEncoder;

    public AuthUseCaseImp(PasswordEncoder passwordEncoder, UserDomainRepositoryPort port) {
        this.passwordEncoder = passwordEncoder;
        this.userPort = port;
    }

    @Override
    public Mono<VerifiedUserCommandResult> verifyUser(UUID userId) {
        return userPort.findByUserId(userId)
                .map(user -> new VerifiedUserCommandResult(user))
                .switchIfEmpty(Mono.error(new IllegalStateException("user not found!")));
    }

    @Override
    public Mono<RegisterUserCommandResult> execute(RegisterUserCommand cmd) {
        return userPort.findByUsername(cmd.username())
                .flatMap(user -> {
                    String passwordHash = passwordEncoder.encode(cmd.password());
                    UserModelDomain userModel = UserModelDomain.register(cmd.username(), cmd.email(), passwordHash);

                    return userPort.save(userModel)
                            .map(saved -> new RegisterUserCommandResult(saved.getId(), saved.getUsername()));
                }).switchIfEmpty(
                        Mono.<RegisterUserCommandResult>error(new IllegalStateException("user already register!")));
    }
}
