package com.example.__WebFlux.domain.products.models;

import java.util.UUID;

public class ProductModelDomain {
    private UUID productId;
    private String sku;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String model;

    public ProductModelDomain(UUID productId, String sku, String name, String shortDescription, String longDescription,
            String model) {
        this.productId = productId;
        this.sku = sku;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.model = model;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

}
