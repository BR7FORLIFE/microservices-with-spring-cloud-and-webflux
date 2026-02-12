package com.archive.match_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.archive.match_service.dto.request.CreateMatchRequestDto;
import com.archive.match_service.dto.response.CreateaMatchResponseDto;
import com.archive.match_service.repository.MongoRepositoryAdapter;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MongoRepositoryAdapter mongoRepositoryAdapter;

    public MatchController(MongoRepositoryAdapter mongoRepositoryAdapter) {
        this.mongoRepositoryAdapter = mongoRepositoryAdapter;
    }

    @PostMapping
    public Mono<ResponseEntity<CreateaMatchResponseDto>> createMatch(@RequestBody CreateMatchRequestDto dto) {
        return mongoRepositoryAdapter.save(dto.name())
                .map(result -> ResponseEntity.status(HttpStatus.CREATED).body(new CreateaMatchResponseDto(result)));
    }
}
