package com.course.productservice.productservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String titile;
    private String description;
    private double price;
    private String image;
    private Category category;
}
