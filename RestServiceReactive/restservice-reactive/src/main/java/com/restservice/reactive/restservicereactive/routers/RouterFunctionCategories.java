package com.restservice.reactive.restservicereactive.routers;

import com.restservice.reactive.restservicereactive.domain.Category;
import com.restservice.reactive.restservicereactive.repositories.CategoryRepository;
import org.reactivestreams.Publisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

@Configuration
public class RouterFunctionCategories {

    @Bean
    org.springframework.web.reactive.function.server.RouterFunction<?> routes(CategoryRepository categoryRepository) {
        return nest(path("/api/v1/categories"),

                RouterFunctions.route(GET(""),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryRepository.findAll(), Category.class))


                .andRoute(GET("/{id}"),
                        request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryRepository.findById(request.pathVariable("id")),Category.class))


                .andRoute(method(POST),
                        request -> {
                            Flux<Category> newCategory = request.bodyToFlux(Category.class);
                            return ServerResponse
                                    .ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(categoryRepository.saveAll(newCategory), Category.class);
                        })

                .andRoute(method(PUT),
                        request -> {
                            Flux<Category> categoryToUpdate = request.bodyToFlux(Category.class);
                            return ServerResponse
                                    .ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(categoryRepository.saveAll(categoryToUpdate), Category.class);
                        })

                .andRoute(DELETE("/{id}"),
                        request -> ServerResponse
                                .ok()
                                .body(categoryRepository.deleteById(request.pathVariable("id")), Void.class))
            );
    }
}
