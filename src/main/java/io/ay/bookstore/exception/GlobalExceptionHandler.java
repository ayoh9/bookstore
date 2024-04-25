package io.ay.bookstore.exception;

import io.ay.bookstore.model.dto.apiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static io.ay.bookstore.utility.CustomLogger.logError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = { IllegalArgumentException.class, ValidationFailedException.class,
            UserAlreadyExistsException.class })
    public ResponseEntity<ApiResponse> badRequestError(Exception ex) {
        logError(ex);
        ApiResponse response = new ApiResponse();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
