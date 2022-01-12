package com.unibuc.ex1curs14.exception;

import org.springframework.http.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DriverAlreadyExistsException.class,
                        MethodArgumentNotValidException.class,
                        InvalidUpdateRequestException.class})
    public ResponseEntity handle(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
