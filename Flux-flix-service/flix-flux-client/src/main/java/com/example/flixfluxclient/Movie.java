package com.example.flixfluxclient;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Data
public class Movie {

    public Movie(String id, String document) {
        this.id = id;
        this.document = document;
    }

    private String id;

    private String document;
}
