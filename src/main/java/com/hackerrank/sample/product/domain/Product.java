package com.hackerrank.sample.product.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private Double price;
    private Double rating;

    private String size;
    private Double weight;
    private String color;

    // Especificaciones flexibles: camera, batteryCapacity, os, brand, etc.
    @ElementCollection
    @CollectionTable(name = "product_specs", joinColumns = @JoinColumn(name = "product_id"))
    @MapKeyColumn(name = "spec_key")
    @Column(name = "spec_value", length = 2000)
    private Map<String, String> specifications = new HashMap<>();

    public Product() {}

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Map<String, String> getSpecifications() { return specifications; }
    public void setSpecifications(Map<String, String> specifications) {
        this.specifications = (specifications == null) ? new HashMap<>() : specifications;
    }
}