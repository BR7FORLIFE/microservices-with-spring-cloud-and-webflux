package com.example.__WebFlux.domain.orders.ports;

import java.util.UUID;

import com.example.__WebFlux.domain.orders.models.OrderModelDomain;

import reactor.core.publisher.Mono;

public interface OrderDomainRepositoryPort {

    Mono<OrderModelDomain> findByOrderId(UUID orderId);

    Mono<OrderModelDomain> save(OrderModelDomain orderModelDomain);
}
