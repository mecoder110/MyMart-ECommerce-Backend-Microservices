package com.murtaza.mymart.product.response;

import lombok.Data;

@Data
public class APIResponse<T> {

    private Integer statusCode;
    private String message;
    private T data;

}
