package com.course.productservice.productservice.services;

import com.course.productservice.productservice.Utils.CommonApi;
import com.course.productservice.productservice.dtos.FakeStoreProductDto;
import com.course.productservice.productservice.exceptions.ErrorMessage;
import com.course.productservice.productservice.exceptions.FakeStoreEmptyException;
import com.course.productservice.productservice.exceptions.ProductNotFoundException;
import com.course.productservice.productservice.models.Category;
import com.course.productservice.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service // this service annotation will create the object of class at time of initialization
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        //calling fake store api to get the product using resttemplatte
        //basic way is to create restTemplate here mannually in each api but this is not good practice
        ///2nd way is to tell to spring to create it's bean of restTemplate and inject it whenever
        // reauired using @Configuration and @Bean annotation.

        //we know that fake store will return fakestore object that we are mapping with fakestroeproductdto.calss
        // handle exception globally
//        int x = 1/0;
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        //here 1st param = url
        //2nd param = responsetype like here we mapped reponse of fakestore object to
        // fakestoreproductdto here conversion is done by spring internally.
        //now convert dto to product
        if(fakeStoreProductDto == null){
//            return null;
            throw new ProductNotFoundException(id, "product with this id does not exist!");
        }

//        return CommonApi.convertFakeStoreDtoToProduct(fakeStoreProductDto);
        return FakeStoreProductDto.convertFakeStoreProductToProduct(fakeStoreProductDto);
    }


    /**
     * why not use List<FakeStoreProductDto>.class => because generic nature of list, array is not generic that's why we have use FakeStoreProductDto[].class as responsetyp
     * we got array of fakestoreproductdtos, we will have convert it to product
     */
    @Override
    public List<Product> getAllProducts(int pageNo, int pageSize, String sortBy) {
      FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        //let's handle exception here
        //gather situation in which exception might occur
        //1. when there is not any prodcut -> we will have to show message "NO PRODUCT EXIST" or "There is no product";
        // I will have to create our custom class with message
//         fakeStoreProductDtos = new FakeStoreProductDto[0];
        if(fakeStoreProductDtos.length == 0){
            throw new FakeStoreEmptyException(ErrorMessage.NO_PRODUCT_EXIST);
        }

        //now implement pagination and sorting
        //convert the array to stream and sort it
        Stream<Product> productStream = FakeStoreProductDto.convertListFakeStoreProductDtoToProductList(fakeStoreProductDtos).stream()
                                .sorted((p1, p2) -> {
                                    switch (sortBy){
                                        case "price":
                                            return Double.compare(p1.getPrice(), p2.getPrice());
                                        case "id":
                                        default:
                                            return Long.compare(p1.getId(), p2.getId());
                                    }
                                });

        //apply pagination
        List<Product> paginatedProducts = productStream
                                .skip((long) pageNo * pageSize)
                                .limit(pageSize)
                                .collect(Collectors.toList());


//        return FakeStoreProductDto.convertListFakeStoreProductDtoToProductList(fakeStoreProductDtos);
        return paginatedProducts;
    }


    @Override
    public Product replaceProduct(Long id, Product product) {
//        restTemplate.put();
        //when go inside this put() you see that return type of this put() is void it means it's not gonna to return object
        //but we want to return this object, this can implementted in two ways.
         /*one way to update the product is by
        get the product by id
        then update product using put method of RESttemplate
        but this won't be consider as a good practice, because we are hitting DB twice which
        which cause two network calls, so do this in one call.
        */

        /*2nd way is, we know fakeStoreServer is return this object but put() is not returnning any object
        //so, inorder to solve this problem we will have to customize the put() method
        //here in put() method, there is a method called execute() which is return object, we can use this method here and make some changes(customization)
        */
        //but we are updating data to outside/exteral system so we will have to first convert prodcut to fakestoreDTO
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitile());
        fakeStoreProductDto.setImage(product.getImage());
        fakeStoreProductDto.setDescription(product.getDescription());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto1 =  restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        if(fakeStoreProductDto1 == null){
            //handle excaption
            //throw ProductNotFoundExeception
            return null;
        }
//        return CommonApi.convertFakeStoreDtoToProduct(fakeStoreProductDto1);
        return FakeStoreProductDto.convertFakeStoreProductToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        //first convert product to fakestoredto
//        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
//        fakeStoreProductDto.setPrice(product.getPrice());
//        fakeStoreProductDto.setDescription(product.getDescription());
//        fakeStoreProductDto.setTitle(product.getTitile());
        Category category = new Category();
        category.setDescription(product.getCategory().getDescription());
//        fakeStoreProductDto.setCategory(category.getDescription());
//        FakeStoreProductDto fakeStoreDto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, fakeStoreProductDto, FakeStoreProductDto.class);
        //above patchForObject is not supporting patch http methods
        // use other way to do this
        //first get this object by id
        //update this object and save
        FakeStoreProductDto oldFakeStoreDtoObject =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        oldFakeStoreDtoObject.setCategory(category.getDescription());
        oldFakeStoreDtoObject.setPrice(product.getPrice());
        oldFakeStoreDtoObject.setTitle(product.getTitile());
        oldFakeStoreDtoObject.setDescription(product.getDescription());
        restTemplate.put("https://fakestoreapi.com/products/" + id, oldFakeStoreDtoObject);
//        restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, oldFakeStoreDtoObject, FakeStoreProductDto.class);
//        return CommonApi.convertFakeStoreDtoToProduct(oldFakeStoreDtoObject);
        return FakeStoreProductDto.convertFakeStoreProductToProduct(oldFakeStoreDtoObject);
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = CommonApi.convertProductToFakeStoreDto(product);
       FakeStoreProductDto fakeStoreProductDto1 =  restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDto, FakeStoreProductDto.class);
       if(fakeStoreProductDto1 == null){
           //handle exeception ProductNotFoundExeception
           return null;
       }
//       return CommonApi.convertFakeStoreDtoToProduct(fakeStoreProductDto1);
        return FakeStoreProductDto.convertFakeStoreProductToProduct(fakeStoreProductDto1);
    }

}
