package com.example.webflux.infrastructure.products.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.webflux.domain.products.models.ProductModelDomain;
import com.example.webflux.domain.products.ports.ProductDomainRepositoryPort;
import com.example.webflux.infrastructure.products.mapper.ProductMapper;
import com.example.webflux.infrastructure.products.repository.postgres.R2dbcPostgresProductRepository;

import reactor.core.publisher.Mono;

@Repository
public class R2dbcProductRepositoryAdapter implements ProductDomainRepositoryPort {

    private final R2dbcPostgresProductRepository productRepository;

    public R2dbcProductRepositoryAdapter(R2dbcPostgresProductRepository r2dbcPostgresProductRepository) {
        this.productRepository = r2dbcPostgresProductRepository;
    }

    @Override
    public Mono<ProductModelDomain> findByProductId(UUID productId) {
        return productRepository.findById(productId)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<ProductModelDomain> findBySku(String sku) {
        return productRepository.findBySku(sku)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Mono<ProductModelDomain> save(ProductModelDomain product) {
        return productRepository.save(ProductMapper.toEntity(product))
                .map(ProductMapper::toDomain);
    }
}
