package com.course.productservice.productservice.controllers;

import com.course.productservice.productservice.exceptions.ProductNotFoundException;
import com.course.productservice.productservice.models.Product;
import com.course.productservice.productservice.services.ProductService;
import com.course.productservice.productservice.utils.TestsUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired //here its mock the acutal depdency
    private ProductController productController;

    @MockBean //but here we need to mocked/clone/copy the object of productService, and if we call any method of the service then call no longer go the actual service method
    private ProductService productService;

    @Test
    void validProductByIdTests() throws ProductNotFoundException {
        Product expectedProduct = TestsUtils.getProduct();
        //here I have call When...thenReturn() for product service (mocking) if controller calls productService
        // by id then return some mocked things
        when(productService.getProductById(1L))
                .thenReturn(expectedProduct);

        ResponseEntity<Product> responseEntity = productController.getProductById(1L);
        Product actualProduct = responseEntity.getBody();

        assertEquals(expectedProduct, actualProduct); //this test case is passed because expected and actual obejccts are equal
    }

    /**
        all test should be perform in the isolation, mean one method should not be depend on the value of other method if so, then we should mock the value other method
        for intance if we are testing method of productController then these method should not be depend on the method of product service class if so then we should mock(hard) value (by
        creating mocked object of this service class) return from that method in product service class
        i.e, if productCOntroller has method called getProductById() which in tern call to productService's getPrdocuctById(Id);
       instead of calling these productService actual object we will call mocked dependency (object) or clone or copy object [which is not a an actual object of product service class]

     */

}