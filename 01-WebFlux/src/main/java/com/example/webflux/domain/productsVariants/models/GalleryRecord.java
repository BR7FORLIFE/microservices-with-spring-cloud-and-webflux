package com.example.webflux.domain.productsVariants.models;

import java.util.List;

record ProductImage(String url, ImageRole role) {}

enum ImageRole {
  THUMBNAIL, MAIN, DETAIL
}

record Gallery(List<ProductImage> images) {
  Gallery {
    if (images.isEmpty())
      throw new IllegalArgumentException("Gallery must contain images");
  }
}

