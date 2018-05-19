package com.restservice.reactive.restservicereactive.bootstrap;

import com.restservice.reactive.restservicereactive.domain.Category;
import com.restservice.reactive.restservicereactive.domain.Vendor;
import com.restservice.reactive.restservicereactive.repositories.CategoryRepository;
import com.restservice.reactive.restservicereactive.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final VendorRepository vendorRepository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(VendorRepository vendorRepository, CategoryRepository categoryRepository) {
        this.vendorRepository = vendorRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // init Vendor Repor

        Vendor juan = new Vendor();
        juan.setId("123");
        juan.setFirstName("Juan");
        juan.setLastName("Higuita");

        Vendor jorge = new Vendor();
        jorge.setId("1234");
        jorge.setFirstName("Jorge");
        jorge.setLastName("Castro");

        Vendor vanesa = new Vendor();
        vanesa.setId("12345");
        vanesa.setFirstName("Venssa");
        vanesa.setLastName("Ramirez");

        Vendor zoe = new Vendor();
        zoe.setId("123456");
        zoe.setFirstName("Zoe");
        zoe.setLastName("Garcia");

        vendorRepository.save(juan).block();
        vendorRepository.save(jorge).block();
        vendorRepository.save(vanesa).block();
        vendorRepository.save(zoe).block();

        System.out.println("The amount of Vendors saved was "+vendorRepository.count().block());

        // init categories

        Category newCategory = new Category();
        newCategory.setId("12333");
        newCategory.setDescription("New Vendor");

        Category expertCategory = new Category();
        expertCategory.setId("1234");
        expertCategory.setDescription("Expert Vendor");

        Category midCategory = new Category();
        midCategory.setId("123");
        midCategory.setDescription("Mid Vendor");

        categoryRepository.save(newCategory).block();
        categoryRepository.save(expertCategory).block();
        categoryRepository.save(midCategory).block();

        System.out.println("The amount of categories saved was " + categoryRepository.count().block());



    }
}
