package com.restservice.reactive.restservicereactive.repositories;

import com.restservice.reactive.restservicereactive.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
