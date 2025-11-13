package com.murtaza.mymart.admin.exception;

import com.murtaza.mymart.admin.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeExc(RuntimeException rte) {
        ApiResponse body = new ApiResponse();
        body.setMessage(rte.getMessage());
        body.setStatusCode(500);
        body.setData(null);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleExc(Exception e) {
        ApiResponse body = new ApiResponse();
        body.setMessage(e.getMessage());
        body.setStatusCode(500);
        body.setData(null);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
