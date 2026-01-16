package com.example.__WebFlux.application.orders.usecases;

import com.example.__WebFlux.application.orders.commands.CreateOrderCommand;
import com.example.__WebFlux.application.orders.commands.CreateOrderCommandResult;

import reactor.core.publisher.Mono;

public interface OrderUseCases {
    Mono<CreateOrderCommandResult> createOrder(CreateOrderCommand createOrderCommand);
}
