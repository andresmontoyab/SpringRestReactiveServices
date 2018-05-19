package com.restservice.reactive.restservicereactive.routers;

import com.restservice.reactive.restservicereactive.domain.Category;
import com.restservice.reactive.restservicereactive.repositories.CategoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class RouterFunctionCategories {

    @Bean
    org.springframework.web.reactive.function.server.RouterFunction<?> routes(CategoryRepository categoryRepository) {
        return RouterFunctions.route(GET("/api/v1/categories"),
                request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryRepository.findAll(), Category.class))
                .andRoute(GET("/api/v1/categories/{id}"),
                        request -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(categoryRepository.findById(request.pathVariable("id")),Category.class));
    }
}
