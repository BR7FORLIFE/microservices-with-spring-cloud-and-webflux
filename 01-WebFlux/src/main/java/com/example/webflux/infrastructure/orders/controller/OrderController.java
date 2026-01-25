package com.example.webflux.infrastructure.orders.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.application.orders.dto.response.CreateOrderResponseDto;
import com.example.webflux.application.orders.orchestator.OrderUseCaseImp;

import reactor.core.publisher.Mono;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class OrderController {

    private final OrderUseCaseImp orderUseCaseImp;

    public OrderController(OrderUseCaseImp orderUseCaseImp) {
        this.orderUseCaseImp = orderUseCaseImp;
    }

    @PostMapping("/users/{userId}/orders")
    public Mono<ResponseEntity<CreateOrderResponseDto>> createOrder() {
        return null;
    }
}
