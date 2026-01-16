package com.example.__WebFlux.application.auth.command;

import com.example.__WebFlux.domain.auth.models.UserModelDomain;

public record VerifiedUserCommandResult(UserModelDomain user) {

}
