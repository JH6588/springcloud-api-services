package com.hd.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public String errorReq( Exception exception) {
        exception.printStackTrace();
        return "YOU GET A ERROR :" + exception.getMessage();
    }


}