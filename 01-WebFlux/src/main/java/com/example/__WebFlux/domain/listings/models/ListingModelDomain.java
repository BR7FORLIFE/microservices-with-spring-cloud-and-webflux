package com.example.__WebFlux.domain.listings.models;

import java.time.Instant;
import java.util.UUID;

public class ListingModelDomain {
    private UUID listingId;
    private UUID productId;
    private Double price;
    private String currency;
    private Boolean isactive;
    private Instant createAt;
    private Instant updateAt;

    public ListingModelDomain(UUID listingId, UUID productId, Double price, String currency, Boolean isactive,
            Instant createAt, Instant updateAt) {
        this.listingId = listingId;
        this.productId = productId;
        this.price = price;
        this.currency = currency;
        this.isactive = isactive;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public UUID getListingId() {
        return listingId;
    }

    public void setListingId(UUID listingId) {
        this.listingId = listingId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Instant updateAt) {
        this.updateAt = updateAt;
    }

}
