package com.course.productservice.productservice.services;

import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.exceptions.FakeStoreEmptyException;
import com.course.productservice.productservice.exceptions.ProductNotFoundException;
import com.course.productservice.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts(int pageNo, int pageSize, String sortBy) ;

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);

    Product addNewProduct(Product product);
}
