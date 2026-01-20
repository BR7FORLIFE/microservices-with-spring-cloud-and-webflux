package com.example.__WebFlux.domain.products.ports;

import java.util.UUID;

import com.example.__WebFlux.domain.products.models.ProductModelDomain;

import reactor.core.publisher.Mono;

public interface ProductDomainRepositoryPort {
    Mono<ProductModelDomain> findByProductId(UUID productId);

    Mono<ProductModelDomain> findBySku(String sku);

    Mono<ProductModelDomain> save(ProductModelDomain product);
}
