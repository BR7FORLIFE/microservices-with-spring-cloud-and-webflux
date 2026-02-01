package com.example.webflux.infrastructure.listings.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webflux.application.listings.command.CreateListingCommand;
import com.example.webflux.application.listings.draft.ProductDraft;
import com.example.webflux.application.listings.dto.request.CreateListingRequestDto;
import com.example.webflux.application.listings.dto.response.CreateListingResponseDto;
import com.example.webflux.application.listings.orchestator.ListingUseCaseImp;
import com.example.webflux.infrastructure.security.CustomUserDetails;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingUseCaseImp listingUseCaseImp;

    public ListingController(ListingUseCaseImp listingUseCaseImp) {
        this.listingUseCaseImp = listingUseCaseImp;
    }

    @PostMapping("/create")
    public Mono<ResponseEntity<CreateListingResponseDto>> createListing(@RequestBody CreateListingRequestDto dto,
            Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        UUID userId = userDetails.getUserId();

        ProductDraft product = new ProductDraft(dto.name(), dto.shortDescription(), dto.longDescription(), dto.model(),
                dto.sku());

        CreateListingCommand cmd = new CreateListingCommand(userId, product, dto.price(), dto.currency(), false);

        return listingUseCaseImp.createListing(cmd)
                .map(result -> ResponseEntity.status(HttpStatus.CREATED).body(new CreateListingResponseDto()));
    }
}
