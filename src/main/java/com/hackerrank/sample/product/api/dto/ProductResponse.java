package com.hackerrank.sample.product.api.dto;

import java.util.Map;

public class ProductResponse {
    public Long id;
    public String name;
    public String imageUrl;      
    public String description;
    public Double price;
    public Double rating;
    public String size;
    public Double weight;
    public String color;
    public Map<String, String> specifications;

    public ProductResponse() {}
}