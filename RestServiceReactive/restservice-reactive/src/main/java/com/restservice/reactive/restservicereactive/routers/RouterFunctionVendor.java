package com.restservice.reactive.restservicereactive.routers;

import com.restservice.reactive.restservicereactive.domain.Vendor;
import com.restservice.reactive.restservicereactive.repositories.VendorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyExtractors.toMono;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;;

@Configuration
public class RouterFunctionVendor {

    @Bean
    RouterFunction<?> routes(VendorRepository vendorRepository) {
        return nest(path("/api/v1/vendors"),

                route(GET(""),
                request -> ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(vendorRepository.findAll(), Vendor.class))


                .andRoute(GET("/{id}"),
                request -> ServerResponse
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(vendorRepository.findById(request.pathVariable("id")), Vendor.class))


                .andRoute(method(HttpMethod.POST),
                request ->{
                    Flux<Vendor> newVendor = request.bodyToFlux(Vendor.class);
                    return ServerResponse
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(vendorRepository.saveAll(newVendor), Vendor.class);
                    }))


                .andRoute(PUT("/{id}"),
                request -> {
                    Flux<Vendor> vendorTOUpdated = request.bodyToFlux(Vendor.class);
                    vendorTOUpdated
                            .map(vendor -> {
                                Vendor vendorToUpdate = vendor;
                                vendorToUpdate.setId(request.pathVariable("id"));
                                return vendorToUpdate;
                            });
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(vendorRepository.saveAll(vendorTOUpdated), Vendor.class);
                    });




//         .and(route(POST("/person"),
//                request -> {
//                    Mono<Person> person = request.body(toMono(Person.class));
//                    return Response.ok().build(repository.savePerson(person));
//                }));
    }
}
