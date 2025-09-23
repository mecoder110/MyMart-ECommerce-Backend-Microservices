package com.murtaza.mymart.product.exception;

import com.murtaza.mymart.product.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<APIResponse<NoResourceFoundException>> exceptionalHandler(NoResourceFoundException noResourceFoundException) {
        APIResponse body = new APIResponse();
        body.setStatusCode(404);
        body.setMessage(noResourceFoundException.getMessage());
        body.setData(noResourceFoundException);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
