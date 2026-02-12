package com.archive.match_service.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "matchs")
@Data
@AllArgsConstructor
public class MatchModel {

    @Id
    private String id;

    private String name;
}
