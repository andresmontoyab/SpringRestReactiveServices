package com.example.fluxflixservices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
@NoArgsConstructor
@Data
public class Movie {

    public Movie(String id, String document) {
        this.id = id;
        this.document = document;
    }

    @Id
    private String id;

    private String document;
}
