package com.example.fluxflixservices;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class MovieServices {

    private  final MovieRepository movieRepository;

    public MovieServices(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Flux<MovieEvent> streamStreams(Movie movie) {

        Flux<Long>  interval = Flux.interval(Duration.ofSeconds(1));

        Flux<MovieEvent> events = Flux.fromStream(Stream.generate(() -> new MovieEvent(movie, new Date(), randonUser())));

        return Flux.zip(interval, events)
                .map(Tuple2::getT2);
    }

    private String randonUser() {
        String [] users = "mkheck, jstracnhm, phil_web, javaexpert".split(",");
        return users[new Random().nextInt(users.length)];
    }

    Mono<Movie> getById(String id) {
        return movieRepository.findById(id);
    }

    Flux<Movie> allMovies() {
        return movieRepository.findAll();
    }

}
