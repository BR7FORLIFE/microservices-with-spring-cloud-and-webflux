package com.example.__WebFlux.infrastructure.products.mapper;

import com.example.__WebFlux.domain.products.models.ProductModelDomain;
import com.example.__WebFlux.infrastructure.products.persistence.ProductEntity;

public class ProductMapper {

    public static ProductModelDomain toDomain(ProductEntity productEntity) {
        return ProductModelDomain.createNew(
                productEntity.getProductId(),
                productEntity.getSku(),
                productEntity.getName(),
                productEntity.getShortDescription(),
                productEntity.getLongDescription(),
                productEntity.getModel());
    }

    public static ProductEntity toEntity(ProductModelDomain productModelDomain) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(null);
        productEntity.setSku(productModelDomain.getSku());
        productEntity.setName(productModelDomain.getName());
        productEntity.setShortDescription(productModelDomain.getShortDescription());
        productEntity.setLongDescription(productModelDomain.getLongDescription());
        productEntity.setModel(productModelDomain.getModel());

        return productEntity;
    }
}
