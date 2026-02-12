package com.archive.match_service.repository.mongo;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.archive.match_service.models.MatchModel;

public interface IMongoRepository extends ReactiveMongoRepository<MatchModel, UUID> {
    
}
