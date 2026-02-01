package com.example.webflux.application.listings.orchestator;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.webflux.application.listings.command.ApproveListingCommand;
import com.example.webflux.application.listings.command.ApproveListingCommandResult;
import com.example.webflux.application.listings.command.CreateListingCommand;
import com.example.webflux.application.listings.command.CreateListingCommandResult;
import com.example.webflux.application.listings.command.PublishListingCommand;
import com.example.webflux.application.listings.command.PublishListingCommandResult;
import com.example.webflux.application.listings.command.RejectedListingCommand;
import com.example.webflux.application.listings.command.RejectedListingCommandResult;
import com.example.webflux.application.listings.command.SuspendListingCommand;
import com.example.webflux.application.listings.command.SuspendListingCommandResult;
import com.example.webflux.application.listings.usecases.ListingUseCase;
import com.example.webflux.application.orders.commands.CreateOrderCommandResult;
import com.example.webflux.domain.auth.ports.UserDomainRepositoryPort;
import com.example.webflux.domain.listings.models.ListingModelDomain;
import com.example.webflux.domain.listings.models.ListingStatusReview;
import com.example.webflux.domain.listings.ports.ListingDomainRepositoryPort;
import com.example.webflux.domain.products.models.ProductModelDomain;
import com.example.webflux.domain.products.ports.ProductDomainRepositoryPort;

import reactor.core.publisher.Mono;

@Service
public class ListingUseCaseImp implements ListingUseCase {

    private final ProductDomainRepositoryPort productPort;
    private final ListingDomainRepositoryPort listingPort;

    public ListingUseCaseImp(ProductDomainRepositoryPort productPort,
            ListingDomainRepositoryPort listingPort) {
        this.productPort = productPort;
        this.listingPort = listingPort;
    }

    @Override
    public Mono<CreateListingCommandResult> createListing(CreateListingCommand cmd) {

        return productPort.findBySku(cmd.product().sku())
                .hasElement()
                .flatMap(exists -> {

                    if (exists) {
                        return Mono.error(
                                new IllegalArgumentException(
                                        "The current product exists! please register another product with different sku!"));
                    }

                    return productPort.save(
                            ProductModelDomain.createNew(
                                    cmd.product().sku(),
                                    cmd.product().name(),
                                    cmd.product().shortDescription(),
                                    cmd.product().longDescription(),
                                    cmd.product().model()))
                            .flatMap(product -> {

                                ListingModelDomain listing = ListingModelDomain.createDraft(
                                        product.getProductId(),
                                        cmd.userId(),
                                        cmd.price(),
                                        cmd.currency());

                                return listingPort.save(listing);
                            })
                            .map(listing -> new CreateListingCommandResult(
                                    listing.getListingId(),
                                    listing.getStatus().name(),
                                    "Listing create succesfull!"));
                });
    }

    @Override
    public Mono<ApproveListingCommandResult> approveListing(ApproveListingCommand cmd) {
        return null;
    }

    @Override
    public Mono<SuspendListingCommandResult> suspendListing(SuspendListingCommand cmd) {
        return null;
    }

    @Override
    public Mono<RejectedListingCommandResult> rejectedListing(RejectedListingCommand cmd) {
        return null;
    }

    @Override
    public Mono<PublishListingCommandResult> publishListing(PublishListingCommand cmd) {

        // // Id generado para las distintas entidadesc
        // UUID listingId = UUID.randomUUID();
        // UUID productId = UUID.randomUUID();

        // // fechas de creacion y actualizacion
        // Instant createAt = Instant.now();
        // Instant updateAt = Instant.now();

        // // creamos las distintas entidades de dominio
        // ProductModelDomain product = ProductModelDomain.createNew(productId,
        // cmd.product().sku(), cmd.product().name(),
        // cmd.product().shortDescription(), cmd.product().longDescription(),
        // cmd.product().model());

        // ListingModelDomain listing = ListingModelDomain.createNew(listingId,
        // productId, cmd.userId(), cmd.price(),
        // cmd.currency(), cmd.isActive(), createAt, updateAt);

        // // retornamos el resultado
        // return productPort.save(product)
        // .then(listingPort.save(listing))
        // .thenReturn(new PublishListingCommandResult());
        return null;
    }
}
