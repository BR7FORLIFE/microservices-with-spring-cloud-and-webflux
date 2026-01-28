package com.example.webflux.domain.listings.services;

import com.example.webflux.domain.listings.models.ListingStatusReview;

public class ListingService {

    private ListingStatusReview status;

    public void submit() {
        this.status = status.submit();
    }

    public void approve() {
        this.status.approve();
    }

    public void requestFix() {
        this.status.requestFix();
    }

    public void reject() {
        this.reject();
    }

    public void resubmit() {
        this.resubmit();
    }

    public ListingStatusReview getStatus() {
        return this.status;
    }

}
