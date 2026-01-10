package com.example.__WebFlux.domain.productsVariants.models;

import java.time.Instant;

public class ProductVariantModel {
    private Integer atributieId;
    private String imageUrl;
    private Gallery gallery;
    private String thumbnailUrl;
    private Instant createAt;
    private Instant updateAt;

    public ProductVariantModel(Integer atributieId, String imageUrl, Gallery gallery, String thumbnailUrl,
            Instant createAt, Instant updateAt) {
        this.atributieId = atributieId;
        this.imageUrl = imageUrl;
        this.gallery = gallery;
        this.thumbnailUrl = thumbnailUrl;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public Integer getAtributieId() {
        return atributieId;
    }

    public void setAtributieId(Integer atributieId) {
        this.atributieId = atributieId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
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
