package com.example.webflux.infrastructure.products.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.webflux.domain.products.models.ProductModelDomain;
import com.example.webflux.domain.products.ports.ProductDomainRepositoryPort;
import com.example.webflux.infrastructure.products.mapper.ProductMapper;
import com.example.webflux.infrastructure.products.repository.postgres.SprinDataProductRepository;

import reactor.core.publisher.Mono;

@Repository
public class R2dbcProductRepositoryAdapter implements ProductDomainRepositoryPort {

    private final SprinDataProductRepository sprinDataProductRepository;

    public R2dbcProductRepositoryAdapter(SprinDataProductRepository sprinDataProductRepository) {
        this.sprinDataProductRepository = sprinDataProductRepository;
    }

    @Override
    public Mono<ProductModelDomain> findByProductId(UUID productId) {
        return sprinDataProductRepository.findById(productId)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<ProductModelDomain> findBySku(String sku) {
        return sprinDataProductRepository.findBySku(sku)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<ProductModelDomain> save(ProductModelDomain product) {

        return null;
    }
}
