package com.course.productservice.productservice.services;

import com.course.productservice.productservice.controllers.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    private final String baseUrl = "https://fakestoreapi.com/products";

    @MockBean
    private RestTemplate restTemplate;

    public void testCancelProductById(){
        //arrange
        Long productId = 1L;

        doNothing().when(restTemplate).delete(baseUrl + "/" + productId);

        //Act
        assertDoesNotThrow(() -> productService.cancelProduct(productId));

        //assert
        verify(restTemplate, times(1)).delete(baseUrl + "/" + productId);
    }
}
