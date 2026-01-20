package com.example.__WebFlux.infrastructure.products.repository.postgres;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.example.__WebFlux.infrastructure.products.persistence.ProductEntity;

public interface SprinDataProductRepository extends ReactiveCrudRepository<ProductEntity, UUID> {
        
}
