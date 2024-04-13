package com.course.productservice.productservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
//current class containing bean methods, it means that spring is ready and if it find a class with
// @configuration annotation then spring will go throught this class and create beans of all the method which
// is annotateed with @Bean inside this class
public class RestTemplateConfig {
    @Bean //tells spring, to create bean of object return by this method
    // at the time of initialization spring goes through all the class
    // (special classes like class with annotaiton @Service, repository, @RestContorller etc)
    // otherwise spring will not go to this class to create it's bean, we will have to add such annotation
    //like @Configuration annotation.
    public RestTemplate getRestTemplate(){
        return new RestTemplateBuilder().build();
    }
}
