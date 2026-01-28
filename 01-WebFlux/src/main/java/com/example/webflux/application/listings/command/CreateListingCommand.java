package com.example.webflux.application.listings.command;

import java.util.UUID;

import com.example.webflux.application.listings.draft.ProductDraft;

public record CreateListingCommand(UUID userId, ProductDraft product, Double price, String currency,
        Boolean isActive) {
    
}
