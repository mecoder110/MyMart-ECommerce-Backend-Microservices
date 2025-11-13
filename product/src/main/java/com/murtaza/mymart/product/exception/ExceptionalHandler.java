package com.murtaza.mymart.product.exception;

import com.murtaza.mymart.product.response.APIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionalHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<APIResponse<NoResourceFoundException>> exceptionalHandler(NoResourceFoundException noResourceFoundException) {
        APIResponse body = new APIResponse();
        body.setStatusCode(404);
        body.setMessage(noResourceFoundException.getMessage());
        body.setData(null);
        log.error(noResourceFoundException.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<APIResponse<String>> exceptionalHandler(NullPointerException npl) {
        APIResponse body = new APIResponse<>();
        body.setStatusCode(500);
        body.setMessage(npl.getMessage());
        body.setData(null);
        log.error(npl.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<APIResponse<String>> exceptionalHandler(Exception e) {
        APIResponse body = new APIResponse<>();
        body.setStatusCode(500);
        body.setMessage(e.getMessage());
        body.setData(null);
        log.error(e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
