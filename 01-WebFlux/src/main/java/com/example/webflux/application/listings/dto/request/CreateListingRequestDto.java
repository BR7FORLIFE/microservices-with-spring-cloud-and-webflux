package com.example.webflux.application.listings.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateListingRequestDto(@NotNull(message = "El sku del producto no puede ser nulo!") String sku,
                @NotNull(message = "El nombre del producto no puede ser nulo!") String name,
                @NotNull(message = "necesita una descripcion corta") String shortDescription,
                @NotNull(message = "necesita una descripcion larga") String longDescription,
                String model, @Positive(message = "El precio debe ser positivo!") Double price, String currency) {

}
