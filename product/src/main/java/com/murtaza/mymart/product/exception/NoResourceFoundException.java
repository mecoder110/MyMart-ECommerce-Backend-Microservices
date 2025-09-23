package com.murtaza.mymart.product.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
public class NoResourceFoundException extends RuntimeException {

    private String resource;
    private String message;
    private int resourceId;
    private int statusCode;

}
