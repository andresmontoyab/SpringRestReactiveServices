package com.example.fluxflixservices;

import lombok.Data;

import java.util.Date;

@Data
public class MovieEvent {

    private Movie movie;
    private Date when;
    private String user;

    public MovieEvent(Movie movie, Date when, String user) {
        this.movie = movie;
        this.when = when;
        this.user = user;
    }
}
