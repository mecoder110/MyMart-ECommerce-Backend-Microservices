package com.murtaza.mymart.customer.exception;

import com.murtaza.mymart.customer.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiResponse> noResourceFoundException(NoResourceFoundException noResourceFoundException) {

        ApiResponse body = new ApiResponse();
        body.setData(noResourceFoundException);
        body.setMessage(noResourceFoundException.getMessage());
        body.setStatusCode(noResourceFoundException.getStatusCode());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> apiException(ApiException apiException) {

        ApiResponse body = new ApiResponse();
        body.setData(apiException);
        body.setMessage(apiException.getMessage());
        body.setStatusCode(apiException.getStatusCode());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> runtimeException(RuntimeException runtimeException) {

        ApiResponse body = new ApiResponse();
        body.setData(runtimeException);
        body.setMessage(runtimeException.getMessage());
        body.setStatusCode(501);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ApiResponse> rxception(Exception exception) {

        ApiResponse body = new ApiResponse();
        body.setData(exception);
        body.setMessage(exception.getMessage());
        body.setStatusCode(501);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
