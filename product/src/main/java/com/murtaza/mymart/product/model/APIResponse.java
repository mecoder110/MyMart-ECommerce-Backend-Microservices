package com.murtaza.mymart.product.model;

import lombok.Data;

@Data
public class APIResponse<T> {

    private int statusCode;
    private String message;

    private T data;

}
