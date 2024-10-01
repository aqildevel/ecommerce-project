package com.course.productservice.productservice.dtos;

import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public static List<Product> convertListFakeStoreProductDtoToProductList(FakeStoreProductDto[] fakeStoreProductDtoList){
//        List<Product> products = fakeStoreProductDtoList.stream()
//                                    .map(fakeStoreProductDto -> convertFakeStoreProductToProduct(fakeStoreProductDto))
//                                    .collect(Collectors.toList());
        return Arrays.stream(fakeStoreProductDtoList)
                .toList().stream()
                .map(FakeStoreProductDto::convertFakeStoreProductToProduct)
                .collect(Collectors.toList());

    }

    public static Product convertFakeStoreProductToProduct(FakeStoreProductDto fakeStoreProductDto){
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
