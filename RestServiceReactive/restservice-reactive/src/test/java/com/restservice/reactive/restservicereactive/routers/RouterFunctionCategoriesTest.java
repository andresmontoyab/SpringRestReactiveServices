package com.restservice.reactive.restservicereactive.routers;

import com.restservice.reactive.restservicereactive.domain.Category;
import com.restservice.reactive.restservicereactive.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;

public class RouterFunctionCategoriesTest {

    WebTestClient webClient;
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        categoryRepository = Mockito.mock(CategoryRepository.class);
        RouterFunctionCategories webConfig = new RouterFunctionCategories();

        org.springframework.web.reactive.function.server.RouterFunction<?> routerFunction = webConfig.routes(categoryRepository);

        webClient = WebTestClient.bindToController(routerFunction).build();
    }

    @Test
    public  void list() {
        Category newCategory = new Category();
        newCategory.setId("12333");
        newCategory.setDescription("New Vendor");

        Category expertCategory = new Category();
        expertCategory.setId("1234");
        expertCategory.setDescription("Expert Vendor");

        Category midCategory = new Category();
        midCategory.setId("123");
        midCategory.setDescription("Mid Vendor");

        BDDMockito.given(categoryRepository.findAll())
                .willReturn(Flux.just(newCategory, expertCategory, midCategory));

        webClient.get().uri("/api/v1/categories")
        .exchange()
        .expectBodyList(Category.class)
        .hasSize(3);


    }

    @Test
    public void getBy() {
        Category midCategory = new Category();
        midCategory.setId("123");
        midCategory.setDescription("Mid Vendor");

        BDDMockito.given(categoryRepository.findById(anyString()))
                .willReturn(Mono.just(midCategory));

        webClient.get()
                .uri("/api/v1/categories/123")
                .exchange()
                .expectBody(Category.class);


    }
}