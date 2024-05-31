package com.course.productservice.productservice.exceptions;

public class FakeStoreEmptyException extends RuntimeException{
    /**
     * Custom Exception (ProductNotFoundException):
     * This exception is thrown when a product is not found.
     * It extends RuntimeException so it doesn’t need to be declared in the method signature.
     */
    public FakeStoreEmptyException(String message){
        super(message);
    }
}
