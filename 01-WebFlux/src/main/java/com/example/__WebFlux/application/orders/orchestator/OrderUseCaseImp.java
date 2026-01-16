package com.example.__WebFlux.application.orders.orchestator;

import org.springframework.stereotype.Service;

import com.example.__WebFlux.application.orders.commands.CreateOrderCommand;
import com.example.__WebFlux.application.orders.commands.CreateOrderCommandResult;
import com.example.__WebFlux.application.orders.usecases.OrderUseCases;
import com.example.__WebFlux.domain.auth.ports.UserDomainRepositoryPort;
import com.example.__WebFlux.domain.orders.models.OrderModelDomain;
import com.example.__WebFlux.domain.orders.ports.OrderDomainRepositoryPort;

import reactor.core.publisher.Mono;

@Service
public class OrderUseCaseImp implements OrderUseCases {

    private final OrderDomainRepositoryPort orderRepositoryPort;
    private final UserDomainRepositoryPort userDomainRepositoryPort;

    public OrderUseCaseImp(OrderDomainRepositoryPort port, UserDomainRepositoryPort userDomainRepositoryPort) {
        this.orderRepositoryPort = port;
        this.userDomainRepositoryPort = userDomainRepositoryPort;
    }

    /**
     * Caso de uso para crear una orden de compra
     */
    @Override
    public Mono<CreateOrderCommandResult> createOrder(CreateOrderCommand cmd) {
        /**
         * Primero verificamos si existe dicha orden de compra
         */
        return orderRepositoryPort.findByOrderId(cmd.orderId())
                .flatMap(existing -> Mono.<CreateOrderCommandResult>error(
                        new IllegalStateException("order already exists!")))
                .switchIfEmpty(
                        userDomainRepositoryPort.findByUserId(cmd.userId())
                                .switchIfEmpty(Mono.error(new IllegalStateException("user not found!")))
                                .flatMap(user -> {
                                    OrderModelDomain orderModel = new OrderModelDomain(
                                            cmd.orderId(),
                                            user.getId(),
                                            cmd.orderDate(),
                                            cmd.status(),
                                            cmd.totalAmount());

                                    return orderRepositoryPort.save(orderModel);
                                })
                                .map(saved -> new CreateOrderCommandResult()));
    }
}
