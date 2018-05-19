package com.example.flixfluxclient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class FlixFluxClientApplication {

	@Bean
	WebClient cliente() {
		return WebClient.create();
	}

	@Bean
	CommandLineRunner demo(WebClient client) {
		return args -> {

			client.get()
					.uri("http://localhost:8080/movies")
					.exchange()
					.flatMapMany(clientResponse -> clientResponse.bodyToFlux(Movie.class)
					.filter(movie ->
							movie.getDocument().toLowerCase().contains("flux".toLowerCase())))
					.subscribe(movie ->
							client.get()
									.uri("http://localhost:8080/movies/{id}/events", movie.getId())
//									.uri("http://localhost:8080/movies/026e5b36-14b2-4908-bbab-6bf252cac83b/events")
									.exchange()
									.flatMapMany(cr -> cr.bodyToFlux(MovieEvent.class))
									.subscribe(eachMovie -> System.out.println(eachMovie.getMovie() + " " + eachMovie.getUser())));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(FlixFluxClientApplication.class, args);
	}
}
