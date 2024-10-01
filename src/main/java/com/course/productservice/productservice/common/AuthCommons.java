package com.course.productservice.productservice.common;

import com.course.productservice.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthCommons {
    private RestTemplate restTemplate;

    public AuthCommons(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String tokenValue){
        //call userService to validate token
        ResponseEntity<UserDto> responseEntity = restTemplate.getForEntity(
                "http://localhost:3131/users/validate/" + tokenValue,
                UserDto.class
        );

        if(responseEntity.getBody() == null){
            //token is invalid
            //throw some exception
            return null;
        }

        return responseEntity.getBody();
    }
}
