package com.example.__WebFlux.infrastructure.products.repository;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.example.__WebFlux.domain.products.models.ProductModelDomain;
import com.example.__WebFlux.domain.products.ports.ProductDomainRepositoryPort;
import com.example.__WebFlux.infrastructure.products.repository.postgres.SprinDataProductRepository;

import reactor.core.publisher.Mono;

@Component
public class R2dbcProductRepositoryAdapter implements ProductDomainRepositoryPort {

    private final SprinDataProductRepository sprinDataProductRepository;

    public R2dbcProductRepositoryAdapter(SprinDataProductRepository sprinDataProductRepository) {
        this.sprinDataProductRepository = sprinDataProductRepository;
    }

    @Override
    public Mono<ProductModelDomain> findByProductId(UUID productId) {
        return null;
    }

    @Override
    public Mono<ProductModelDomain> findBySku(String sku) {

        return null;
    }

    @Override
    public Mono<ProductModelDomain> save(ProductModelDomain product) {

        return null;
    }
}
