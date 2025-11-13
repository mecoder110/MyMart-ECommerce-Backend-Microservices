package com.murtaza.mymart.admin.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private int statusCode;
    private T Data;
}
