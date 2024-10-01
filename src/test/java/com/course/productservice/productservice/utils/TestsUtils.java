package com.course.productservice.productservice.utils;

import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;

public class TestsUtils {
    public static Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setTitile("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops");
        product.setPrice(109.95);
        product.setDescription("Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday");
        product.setImage("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg");
        Category category = new Category();
        category.setDescription("men's clothing");
        category.setId(null);
        product.setCategory(category);
        return product;
    }
}
