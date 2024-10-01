package com.course.productservice.productservice.dtos;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private String email;
    private List<Role> roles;
}
