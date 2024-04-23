package com.course.productservice.productservice.config;

import com.course.productservice.productservice.Utils.CommonApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonApiConfig {

    @Bean
    public CommonApi getCommonApiInstance(){
        return new CommonApi();
    }
}
