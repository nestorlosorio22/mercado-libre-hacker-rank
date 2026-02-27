package com.hackerrank.sample.product.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackerrank.sample.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
