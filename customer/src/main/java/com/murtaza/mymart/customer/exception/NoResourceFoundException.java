package com.murtaza.mymart.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoResourceFoundException extends RuntimeException {


    private String resource;
    private int resourceId;
    private String message;
    private int statusCode;

    public NoResourceFoundException(String resource, String message, int statusCode) {
        super(message);
        this.resource = resource;
        this.message = message;
        this.statusCode = statusCode;

    }
}
