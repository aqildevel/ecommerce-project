package com.course.productservice.productservice.controllers;

import com.course.productservice.productservice.models.Product;
import com.course.productservice.productservice.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    //at the time of injection, spring will have to inject it's object/bean and this is possible when
    // we add @Service annotation on implemented class
    ProductController(ProductService productService){
        this.productService = productService;
    }

    /*
    //in case of multiple parameter we pass in the path like
    localhost:8080/products/1/aqil
    @GatMapping("/{id}/{name}")
      public Product getProductById(@PathVariable("id") Long id, @PathVariable("name") String name){
    }

    @GetMapping("/id") this is become end point but not parameter that we need in the path
    so use @GetMapping("/{id}")
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    //localhost:8080/products/ => @GetMapping("/")
    //localhost:8080/product => @GetMapping("")
    @GetMapping("")
    public List<Product> getALlProducts(){
        return productService.getAllProducts();
    }


}
