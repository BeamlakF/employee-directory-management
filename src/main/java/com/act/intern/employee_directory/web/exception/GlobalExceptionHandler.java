package com.act.intern.employee_directory.web.exception;

import com.act.intern.employee_directory.domain.exception.DuplicateResourceException;
import com.act.intern.employee_directory.domain.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleNotFound(ResourceNotFoundException ex) {

        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error", ex.getMessage()
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleDuplicate(DuplicateResourceException ex) {

        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 409,
                "error", ex.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidation(IllegalArgumentException ex) {

        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 400,
                "error", ex.getMessage()
        );
    }
}