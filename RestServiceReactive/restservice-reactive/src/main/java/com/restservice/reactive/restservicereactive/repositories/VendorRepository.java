package com.restservice.reactive.restservicereactive.repositories;

import com.restservice.reactive.restservicereactive.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
