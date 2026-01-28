package com.example.webflux.domain.listings.models;

import java.time.Instant;
import java.util.UUID;

public final class ListingModelDomain {
    private final UUID listingId;
    private final UUID productId;
    private final UUID userId;
    private final Double price;
    private final String currency;
    private final Boolean isactive;
    private final String status;
    private final Instant createAt;
    private final Instant updateAt;

    private ListingModelDomain(UUID listingId, UUID productId, UUID userId, Double price, String currency,
            Boolean isactive, String status,
            Instant createAt, Instant updateAt) {
        this.listingId = listingId;
        this.productId = productId;
        this.userId = userId;
        this.price = price;
        this.currency = currency;
        this.isactive = isactive;
        this.status = status;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public static ListingModelDomain createNew(UUID listingId, UUID productId, UUID userId, Double price,
            String currency,
            Boolean isactive,
            String status,
            Instant createAt, Instant updateAt) {
        return new ListingModelDomain(listingId, productId, userId, price, currency, isactive, status, createAt,
                updateAt);
    }

    public UUID getListingId() {
        return listingId;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

}
