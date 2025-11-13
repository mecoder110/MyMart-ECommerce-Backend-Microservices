package com.murtaza.mymart.order.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private int statusCode;
    private T Data;
}
