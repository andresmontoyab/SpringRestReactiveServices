//package com.example.fluxflixservices;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/movies")
//public class MovieController {
//
//    private final MovieServices movieServices;
//
//    public MovieController(MovieServices movieServices) {
//        this.movieServices = movieServices;
//    }
//
//    @GetMapping(path = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<MovieEvent> events(@PathVariable String id) {
//        return movieServices.getById(id)
//                .flatMapMany(movieServices::streamStreams);
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public Flux<Movie> allMovies() {
//        return movieServices.allMovies();
//    }
//
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<Movie> byId(@PathVariable  String id) {
//        return movieServices.getById(id);
//    }
//}
