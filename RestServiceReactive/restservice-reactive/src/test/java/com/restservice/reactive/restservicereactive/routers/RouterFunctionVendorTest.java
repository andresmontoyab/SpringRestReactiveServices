package com.restservice.reactive.restservicereactive.routers;

import com.restservice.reactive.restservicereactive.domain.Vendor;
import com.restservice.reactive.restservicereactive.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class RouterFunctionVendorTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;

    @Before
    public void setUp() throws Exception {

        vendorRepository = Mockito.mock(VendorRepository.class);
        RouterFunctionVendor webCofig = new RouterFunctionVendor();

        RouterFunction<?> routerFunction = webCofig.routes(vendorRepository);

        webTestClient = WebTestClient.bindToController(routerFunction).build();
    }

    @Test
    public void listAllVendorTest() {

        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(new Vendor(), new Vendor()));

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void getVendorByIdTest() {

        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(new Vendor()));

        webTestClient.get()
                .uri("/api/v1/vendors/1")
                .exchange()
                .expectBody(Vendor.class);
    }

    @Test
    public void createVendor() {

        Vendor vendorToAdd = new Vendor();
        vendorToAdd.setId("12");
        vendorToAdd.setFirstName("Andres");
        vendorToAdd.setLastName("Gaviria");

        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(vendorToAdd));

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(Flux.just(vendorToAdd), Vendor.class) //parametro que le entra al servicio
                .exchange()
                .expectStatus().isOk();

    }
}