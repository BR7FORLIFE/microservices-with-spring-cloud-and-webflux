package com.example.__WebFlux.application.auth.orchestator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.__WebFlux.application.auth.command.RegisterUserCommand;
import com.example.__WebFlux.application.auth.command.RegisterUserCommandResult;
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
    public Mono<RegisterUserCommandResult> execute(RegisterUserCommand cmd) {
        return userPort.existsByUsername(cmd.username())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalStateException("User already exits!"));
                    }

                    String passwordHash = passwordEncoder.encode(cmd.password());
                    UserModelDomain user = UserModelDomain.register(cmd.username(), cmd.email(), passwordHash);

                    return userPort.save(user)
                            .map(saved -> new RegisterUserCommandResult(saved.getId(), saved.getUsername()));
                });
    }
}
