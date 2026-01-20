package com.example.__WebFlux.application.products.orchestator;

import org.springframework.stereotype.Service;

import com.example.__WebFlux.application.products.commands.RegisterProductCommand;
import com.example.__WebFlux.application.products.commands.RegisterProductCommandResult;
import com.example.__WebFlux.application.products.usecases.ProductUseCases;
import com.example.__WebFlux.domain.products.models.ProductModelDomain;
import com.example.__WebFlux.domain.products.ports.ProductDomainRepositoryPort;

import reactor.core.publisher.Mono;

@Service
public class ProductUseCaseImp implements ProductUseCases {

    private final ProductDomainRepositoryPort port;

    public ProductUseCaseImp(ProductDomainRepositoryPort port) {
        this.port = port;
    }

    @Override
    public Mono<RegisterProductCommandResult> registerProduct(RegisterProductCommand cmd) {
        return port.findBySku(cmd.sku())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new IllegalStateException("The current product is already registered"));
                    }
                    ProductModelDomain product = ProductModelDomain.createNew(
                            cmd.sku(),
                            cmd.name(),
                            cmd.shortDescription(),
                            cmd.longDescription(),
                            cmd.model());
                    return port.save(product);
                })
                .map(saved -> new RegisterProductCommandResult(
                        saved.getProductId().toString(),
                        saved.getName()));
    }
}
