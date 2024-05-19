package com.course.productservice.productservice.Utils;

import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommonApi {
    public static List<Product> getListOfProductFromFakestoreproductarray(FakeStoreProductDto[] fakeStoreProductDtos) {
        //I have to use functional programming mean lamda express
        List<Product> products = Arrays.stream(fakeStoreProductDtos)
                                .map(fakeStoreProductDto -> convertFakeStoreDtoToProduct(fakeStoreProductDto))
                                .collect(Collectors.toList());
        return products;
    }

    public static Product convertFakeStoreDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
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

    public static FakeStoreProductDto convertProductToFakeStoreDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitile());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImage());
        Category category = new Category();
        category.setDescription(product.getCategory().getDescription());
        fakeStoreProductDto.setCategory(category.getDescription());
        return fakeStoreProductDto;
    }
}
