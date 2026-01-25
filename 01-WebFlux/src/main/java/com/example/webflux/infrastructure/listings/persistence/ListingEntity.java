package com.example.webflux.infrastructure.listings.persistence;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name = "listings")
@Data
public class ListingEntity {    
    @Id
    @Column("listing_id")
    private UUID listingId;

    @Id
    @Column("product_id")
    private UUID productId;

    @Column("price")
    private Double price;

    @Column("currency")
    private String currency;

    @Column("is_active")
    private Boolean isActive;

    @Column("create_at")
    private Instant createAt;

    @Column("update_at")
    private Instant updateAt;
}
