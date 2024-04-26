package com.course.productservice.productservice.controllers;

import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.models.Product;
import com.course.productservice.productservice.services.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
        /*
        ResponseEntity:-
        => when send response from server, here we are only sending product but we haven't handle http status code, header etc and
        these all are executing be default by sprinb, however, we can set or create our own http status code and set headers and show
        this to the end users, and this we do with the use of ResponseEntity class, this class has some attributes such as status code, header and body.
       => ResponseEntity is a class provided by Spring Framework that represents an HTTP response, including status code,
       headers, and body. It's commonly used in Spring MVC and Spring WebFlux applications for handling HTTP responses in a flexible and convenient manner
       example:
            // For simplicity, let's create a sample response body
        String responseBody = "Hello, world!";

        // Build a ResponseEntity with a status code, headers, and body
        return ResponseEntity.ok()
                             .header("Custom-Header", "Value")
                             .body(responseBody);
        **/
        Product product = productService.getProductById(id);
        if(product == null){
            return new ResponseEntity<>(null, HttpStatusCode.valueOf(330));
        }
//        return new ResponseEntity<>(product, HttpStatusCode.valueOf(200));
        return ResponseEntity
                .ok()
                .header(
                        "custom-header", "200"
                )
                .body(product);
    }

    //localhost:8080/products/ => @GetMapping("/")
    //localhost:8080/product => @GetMapping("")
    @GetMapping("")
    public List<Product> getALlProducts(){
        return productService.getAllProducts();
    }

    /*
    we update a product in two ways
    => PUT : we have given an Id, Update here mean replace old product with this ID with then same prodcut with updated value it means
    all the attribute of old product object will get updated with new one, if in the old object there was a attribute with value but when
    we send new object which does not have any value let say null value then that's attribute now will have null value because it got updated
    => PATCH: when we want update partial field or variable of this product.


     */
    //PUT api
    @PutMapping("/{id}")
    public Product updateProductById(@PathVariable Long id, @RequestBody Product product){

        return productService.replaceProduct(id, product);
    }


}
