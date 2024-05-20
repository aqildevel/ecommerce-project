package com.course.productservice.productservice.dtos;

import lombok.Data;

@Data
public class ExceptionDto {
    // here whatever information you want to send to othe end user you can add those information here and pass to the client
    private String message;
    private String resolution;
}
