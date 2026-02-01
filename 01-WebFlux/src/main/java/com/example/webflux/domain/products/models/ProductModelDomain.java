package com.example.webflux.domain.products.models;

import java.util.UUID;

public final class ProductModelDomain {
    private final UUID productId;
    private final String sku;
    private final String name;
    private final String shortDescription;
    private final String longDescription;
    private final String model;

    private ProductModelDomain(
            UUID productId,
            String sku,
            String name,
            String shortDescription,
            String longDescription,
            String model) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.model = model;
    }

    public static ProductModelDomain createNew(
            String sku,
            String name,
            String shortDescription,
            String longDescription,
            String model) {
        return new ProductModelDomain(
                UUID.randomUUID(),
                sku,
                name,
                shortDescription,
                longDescription,
                model);
    }

    public static ProductModelDomain createNew(
            UUID id,
            String sku,
            String name,
            String shortDescription,
            String longDescription,
            String model) {
        return new ProductModelDomain(
                id,
                sku,
                name,
                shortDescription,
                longDescription,
                model);
    }

    public ProductModelDomain withId(UUID id) {
        return new ProductModelDomain(
                id,
                sku,
                name,
                shortDescription,
                longDescription,
                model);
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public String getModel() {
        return model;
    }

}
