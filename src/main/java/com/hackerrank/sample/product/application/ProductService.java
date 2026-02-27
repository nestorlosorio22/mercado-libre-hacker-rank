package com.hackerrank.sample.product.application;

import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.product.domain.Product;
import com.hackerrank.sample.product.infrastructure.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void create(Product p) {
        if (p == null || p.getId() == null) {
            throw new BadResourceRequestException("Product id is required.");
        }
        if (p.getName() == null || p.getName().trim().isEmpty()) {
            throw new BadResourceRequestException("Product name is required.");
        }
        if (repo.existsById(p.getId())) {
            throw new BadResourceRequestException("Product with same id exists.");
        }
        repo.save(p);
    }

    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("No product with given id found."));
    }

    public List<Product> getByIds(List<Long> ids) {
        List<Product> found = repo.findAllById(ids);
        if (found.isEmpty()) {
            throw new NoSuchResourceFoundException("No products found for given ids.");
        }
        return found;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}