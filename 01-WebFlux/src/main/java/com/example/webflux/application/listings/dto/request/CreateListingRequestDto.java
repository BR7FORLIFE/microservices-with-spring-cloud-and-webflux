package com.example.webflux.application.listings.dto.request;

import java.util.UUID;

import com.example.webflux.application.listings.draft.ProductDraft;
import com.example.webflux.domain.listings.models.CurrencyEnum;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateListingRequestDto(@org.hibernate.validator.constraints.UUID UUID userId,
        @Positive @NotNull Double price, @NotNull CurrencyEnum currency, ProductDraft product) {

}
