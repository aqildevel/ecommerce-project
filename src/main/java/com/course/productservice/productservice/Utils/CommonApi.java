package com.course.productservice.productservice.Utils;

import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonApi {
    public List<Product> getListOfProductFromFakestoreproductarray(FakeStoreProductDto[] fakeStoreProductDtos) {
        //I have to use functional programming mean lamda express
        List<Product> products = Arrays.stream(fakeStoreProductDtos)
                                .map(fakeStoreProductDto -> convertFakeStoreDtoToProduct(fakeStoreProductDto))
                                .collect(Collectors.toList());
        return products;
    }

    public Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitile(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
