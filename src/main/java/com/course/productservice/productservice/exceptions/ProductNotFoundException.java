package com.course.productservice.productservice.exceptions;

public class ProductNotFoundException extends Exception{
    private Long id;
    private String message;

    public ProductNotFoundException(Long id, String message){
        super(message);
        this.id = id;
    }
}
