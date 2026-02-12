package com.archive.match_service.repository;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.archive.match_service.models.MatchModel;
import com.archive.match_service.repository.mongo.IMongoRepository;

import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter {

    private final IMongoRepository iMongoRepository;

    public MongoRepositoryAdapter(IMongoRepository repository) {
        this.iMongoRepository = repository;
    }

    public Mono<String> save(String name) {
        return iMongoRepository.save(new MatchModel(null, name)).thenReturn("match model save sucessfull");

    }
}
