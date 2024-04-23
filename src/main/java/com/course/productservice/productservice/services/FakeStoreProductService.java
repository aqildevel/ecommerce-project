package com.course.productservice.productservice.services;

import com.course.productservice.productservice.Utils.CommonApi;
import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service // this service annotation will create the object of class at time of initialization
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private CommonApi commonApi;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Long id) {
        //calling fake store api to get the product using resttemplatte
        //basic way is to create restTemplate here mannually in each api but this is not good practice
        ///2nd way is to tell to spring to create it's bean of restTemplate and inject it whenever
        // reauired using @Configuration and @Bean annotation.

        //we know that fake store will return fakestore object that we are mapping with fakestroeproductdto.calss
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        //here 1st param = url
        //2nd param = responsetype like here we mapped reponse of fakestore object to
        // fakestoreproductdto here conversion is done by spring internally.
        //now convert dto to product
        if(fakeStoreProductDto == null){
            return null;
        }

        return commonApi.convertFakeStoreDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
      FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
      //why not use List<FakeStoreProductDto>.class => because generic nature of list, array is not generic that's why we have use FakeStoreProductDto[].class as responsetyp
      //we got array of fakestoreproductdtos, we will have convert it to product

      return commonApi.getListOfProductFromFakestoreproductarray(fakeStoreProductDtos);
    }

}
