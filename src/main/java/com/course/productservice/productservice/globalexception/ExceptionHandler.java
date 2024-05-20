package com.course.productservice.productservice.globalexception;

import com.course.productservice.productservice.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
//this annotation handle exceptions thrown by any controllers within the application, it often used with @ExceptionHandler
//method to define how different exceptions should be handled.
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler (ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleAirthmeticException(){
        //instead  of passing only status code, we can pass message and resolution to solve this issue, for this we will use exceptionDto to send details to the end user
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Something went wrong");
        exceptionDto.setResolution("Nothing can be done");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
