package com.example.__WebFlux.application.auth.command;

import java.util.UUID;

public record RegisterUserCommandResult(UUID user_id, String username) {

}
