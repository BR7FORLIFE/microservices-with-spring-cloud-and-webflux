package com.example.__WebFlux.application.auth.command;

public record RegisterUserCommand(String username, String password, String email) {

}
