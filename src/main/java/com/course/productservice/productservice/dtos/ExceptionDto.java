package com.course.productservice.productservice.dtos;

import lombok.Data;

@Data
public class ExceptionDto {
    // here whatever information you want to send to othe end user you can add those information here and pass to the client
    private int statusCode;
    private String message;
    private String details;

    public ExceptionDto(int statusCode, String message, String details){
        this.statusCode = statusCode;
        this.message = message;
        this.details = details;
    }

    public ExceptionDto() {

    }
}
