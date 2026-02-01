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
    private final ListingStatusReview status;
    private final Instant createAt;
    private final Instant updateAt;

    private ListingModelDomain(UUID listingId, UUID productId, UUID userId, Double price, String currency,
            Boolean isactive, ListingStatusReview status,
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
            ListingStatusReview status,
            Instant createAt, Instant updateAt) {
        return new ListingModelDomain(listingId, productId, userId, price, currency, isactive, status, createAt,
                updateAt);
    }

    public static ListingModelDomain createDraft(
            UUID productId,
            UUID userId,
            Double price,
            String currency) {
        Instant now = Instant.now();
        return new ListingModelDomain(
                UUID.randomUUID(),
                productId,
                userId,
                price,
                currency,
                false,
                ListingStatusReview.DRAFT,
                now,
                now);
    }

    public ListingModelDomain submit() {
        return changeStatus(status.submit());
    }

    public ListingModelDomain approve() {
        return changeStatus(status.approve());
    }

    public ListingModelDomain requestFix() {
        return changeStatus(status.requestFix());
    }

    public ListingModelDomain reject() {
        return changeStatus(status.reject());
    }

    private ListingModelDomain changeStatus(ListingStatusReview newStatus) {
        return new ListingModelDomain(
                listingId,
                productId,
                userId,
                price,
                currency,
                isactive,
                newStatus,
                createAt,
                Instant.now());
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

    public ListingStatusReview getStatus() {
        return status;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

}
