package com.hackerrank.sample.product.infrastructure;


import com.hackerrank.sample.product.domain.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ProductSeeder implements CommandLineRunner {

    private final ProductRepository repo;

    public ProductSeeder(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        if (repo.count() > 0) return;

        Product phone = new Product();
        phone.setId(1L);
        phone.setName("Iphone");
        phone.setDescription("Iphone 16");
        phone.setPrice(899.99);
        phone.setRating(4.6);
        phone.setSize("6.1in");
        phone.setWeight(0.174);
        phone.setColor("Black");
        phone.setSpecifications(Map.of(
                "batteryCapacity", "4000mAh",
                "year", "2026",
                "memory", "8GB",
                "storage", "256GB",
                "brand", "ACME",
                "operatingSystem", "Apple"
        ));

        Product iphone = new Product();
        iphone.setId(2L);
        iphone.setName("Iphone");
        iphone.setDescription("Iphone 16");
        iphone.setPrice(1200.99);
        iphone.setRating(4.6);
        iphone.setSize("6.1in");
        iphone.setWeight(0.174);
        iphone.setColor("Red");
        iphone.setSpecifications(Map.of(
                "batteryCapacity", "4000mAh",
                "year", "2024",
                "memory", "16GB",
                "storage", "1024GB",
                "brand", "ACME",
                "operatingSystem", "Apple"
        ));

        repo.save(phone);
        repo.save(iphone);
    }
}