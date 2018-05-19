package com.example.flixfluxclient;

import lombok.Data;

import java.util.Date;

public class MovieEvent {

    private Movie movie;
    private Date when;
    private String user;

    public MovieEvent(Movie movie, Date when, String user) {
        this.movie = movie;
        this.when = when;
        this.user = user;
    }

    public MovieEvent() {
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
