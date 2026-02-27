package com.hackerrank.sample.product.api;


import com.hackerrank.sample.product.application.ComparisonService;
import com.hackerrank.sample.product.application.ProductService;
import com.hackerrank.sample.product.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ComparisonService comparisonService;

    public ProductController(ProductService productService, ComparisonService comparisonService) {
        this.productService = productService;
        this.comparisonService = comparisonService;
    }

    // CRUD mínimo (opcional, pero útil)
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Product product) {
        productService.create(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    /**
     * Endpoint principal solicitado por el enunciado:
     * GET /products/compare?ids=1,2,3&fields=name,price,specifications
     */
    @GetMapping("/compare")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> compare(
            @RequestParam("ids") String ids,
            @RequestParam(value = "fields", required = false) String fields
    ) {
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::valueOf)
                .collect(Collectors.toList());

        Set<String> fieldSet = parseFields(fields);

        return comparisonService.compare(idList, fieldSet);
    }

    private Set<String> parseFields(String fields) {
        if (fields == null || fields.trim().isEmpty()) {
            return Collections.emptySet();
        }
        return Arrays.stream(fields.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
