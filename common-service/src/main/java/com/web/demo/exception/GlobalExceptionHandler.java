package com.web.demo.exception;

import com.web.demo.response.GlobalResponse;
import com.web.demo.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserAlreadyExistsException.class})
    public GlobalResponse handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }

    @ExceptionHandler(value = UserNotExistsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GlobalResponse handleUserNotFoundException(UserNotExistsException exception) {
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}
