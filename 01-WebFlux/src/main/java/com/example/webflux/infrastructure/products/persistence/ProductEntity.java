package com.example.webflux.infrastructure.products.persistence;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Table(name = "products")
@Data
public class ProductEntity {
    @Id
    @Column("product_id")
    private UUID productId;

    @Column("sku")
    private String sku;

    @Column("name_product")
    private String name;

    @Column("short_description")
    private String shortDescription;

    @Column("long_description")
    private String longDescription;

    @Column("model")
    private String model;
}
