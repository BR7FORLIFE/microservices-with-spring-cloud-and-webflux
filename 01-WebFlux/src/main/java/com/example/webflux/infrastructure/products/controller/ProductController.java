package com.example.webflux.infrastructure.products.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.application.products.commands.RegisterProductCommand;
import com.example.webflux.application.products.dto.RegisterProductRequestDto;
import com.example.webflux.application.products.dto.RegisterProductResponseDto;
import com.example.webflux.application.products.usecases.ProductUseCases;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCases productUseCases;

    public ProductController(ProductUseCases productUseCases) {
        this.productUseCases = productUseCases;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<RegisterProductResponseDto>> registerProduct(
            @RequestBody @Valid RegisterProductRequestDto productDto) {
        RegisterProductCommand cmd = new RegisterProductCommand(productDto.name(), productDto.sku(),
                productDto.shortDescription(), productDto.longDescription(), productDto.model());

        return productUseCases.registerProduct(cmd)
                .map(result -> ResponseEntity.ok()
                        .body(new RegisterProductResponseDto(result.productId(), result.name(),
                                "Product register succesfull!")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).build()));

    }
}
