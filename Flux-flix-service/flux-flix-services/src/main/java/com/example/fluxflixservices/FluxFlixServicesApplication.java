package com.example.fluxflixservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class FluxFlixServicesApplication {

	@Bean
	RouterFunction<ServerResponse> routes(MovieServices movieServices) {
		return route(GET("/movies"),
					request -> ok().body(movieServices.allMovies(), Movie.class))
				.andRoute(GET("/movies/{id}"),
					request -> ok().body(movieServices.getById(request.pathVariable("id")), Movie.class))
				.andRoute(GET("/movies/{id}/events"),
					request ->
						ok()
							.contentType(MediaType.TEXT_EVENT_STREAM)
							.body(movieServices.getById(request.pathVariable("id"))
								.flatMapMany(movieServices::streamStreams), MovieEvent.class));
	}

	@Bean
	CommandLineRunner demo(MovieRepository movieRepository) {
	 return args -> {
		 movieRepository.deleteAll()
				 .subscribe(null, null,
						 () -> Stream.of("Aeon Flux", "Enter the Mono<Void>", "The Fluxinator",
						 "Silence of the Lambdas", "Reactives Mongos on a Plane",
						 "Y tu mono Tambien", "Attack of the fluxxes", "Back to the Future")
						 .map(name -> new Movie(UUID.randomUUID().toString(), name))
						 .forEach(movie -> movieRepository.save(movie).subscribe(System.out::println)));

	 };
	}
	private String randomGenre() {
		String [] genres = "horror, drama, action, documentary".split(",");
		return genres[new Random().nextInt(genres.length)];
	}

	public static void main(String[] args) {
		SpringApplication.run(FluxFlixServicesApplication.class, args);
	}
}
