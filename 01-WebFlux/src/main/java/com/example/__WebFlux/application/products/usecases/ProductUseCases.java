package com.example.__WebFlux.application.products.usecases;

import com.example.__WebFlux.application.products.commands.RegisterProductCommand;
import com.example.__WebFlux.application.products.commands.RegisterProductCommandResult;

import reactor.core.publisher.Mono;

public interface ProductUseCases {
    Mono<RegisterProductCommandResult> registerProduct(RegisterProductCommand cmd);
}
