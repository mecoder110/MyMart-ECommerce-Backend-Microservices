package com.murtaza.mymart.customer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException extends RuntimeException {

    private String resource;
    private int resourceId;
    private String message;
    private int statusCode;

    public ApiException(String resource, String message, int statusCode) {
        super(message);
        this.resource = resource;
        this.message = message;
        this.statusCode = statusCode;

    }
}
