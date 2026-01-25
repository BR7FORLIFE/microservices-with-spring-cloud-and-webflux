package com.example.webflux.infrastructure.listings.mapper;

import com.example.webflux.domain.listings.models.ListingModelDomain;
import com.example.webflux.infrastructure.listings.persistence.ListingEntity;

public class ListingMapper {

    public static ListingModelDomain toDomain(ListingEntity listingEntity) {
        return new ListingModelDomain(
                listingEntity.getListingId(),
                listingEntity.getProductId(),
                listingEntity.getPrice(),
                listingEntity.getCurrency(),
                listingEntity.getIsActive(),
                listingEntity.getCreateAt(),
                listingEntity.getUpdateAt());
    }

    public static ListingEntity toEntity(ListingModelDomain listingModelDomain) {
        ListingEntity listingEntity = new ListingEntity();
        listingEntity.setListingId(null);
        listingEntity.setProductId(listingModelDomain.getProductId());
        listingEntity.setPrice(listingModelDomain.getPrice());
        listingEntity.setCurrency(listingModelDomain.getCurrency());
        listingEntity.setIsActive(listingModelDomain.getIsactive());
        listingEntity.setCreateAt(listingModelDomain.getCreateAt());
        listingEntity.setUpdateAt(listingModelDomain.getUpdateAt());

        return listingEntity;
    }
}
