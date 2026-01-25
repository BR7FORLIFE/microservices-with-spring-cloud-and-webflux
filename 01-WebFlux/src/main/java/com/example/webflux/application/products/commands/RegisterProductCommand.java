package com.example.webflux.application.products.commands;

public record RegisterProductCommand(String name, String sku, String shortDescription, String longDescription,
        String model) {

}
