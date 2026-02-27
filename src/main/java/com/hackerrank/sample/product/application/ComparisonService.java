package com.hackerrank.sample.product.application;


import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.product.domain.Product;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComparisonService {

    private final ProductService productService;

    public ComparisonService(ProductService productService) {
        this.productService = productService;
    }

    public List<Map<String, Object>> compare(List<Long> ids, Set<String> fields) {
        if (ids == null || ids.isEmpty()) {
            throw new BadResourceRequestException("ids is required.");
        }

        List<Product> products = productService.getByIds(ids);

        // Si no mandan fields, devolvemos un set por defecto "sano" para comparación.
        Set<String> effectiveFields = (fields == null || fields.isEmpty())
                ? defaultFields()
                : normalize(fields);

        List<Map<String, Object>> response = new ArrayList<>();
        for (Product p : products) {
            response.add(project(p, effectiveFields));
        }
        return response;
    }

    private Set<String> defaultFields() {
        return new LinkedHashSet<>(Arrays.asList(
                "id", "name", "price", "rating", "size", "weight", "color", "specifications"
        ));
    }

    private Set<String> normalize(Set<String> fields) {
        Set<String> normalized = new LinkedHashSet<>();
        for (String f : fields) {
            if (f == null) continue;
            normalized.add(f.trim().toLowerCase(Locale.ROOT));
        }
        return normalized;
    }

    private Map<String, Object> project(Product p, Set<String> fields) {
        Map<String, Object> out = new LinkedHashMap<>();
        for (String f : fields) {
            switch (f) {
                case "id": out.put("id", p.getId()); break;
                case "name": out.put("name", p.getName()); break;
                case "description": out.put("description", p.getDescription()); break;
                case "price": out.put("price", p.getPrice()); break;
                case "rating": out.put("rating", p.getRating()); break;
                case "size": out.put("size", p.getSize()); break;
                case "weight": out.put("weight", p.getWeight()); break;
                case "color": out.put("color", p.getColor()); break;
                case "specifications": out.put("specifications", p.getSpecifications()); break;
                default:
                    // Puedes optar por:
                    // 1) ignorar campos desconocidos (actual)
                    // 2) lanzar 400 si fields trae algo inválido (más estricto)
                    break;
            }
        }
        return out;
    }
}