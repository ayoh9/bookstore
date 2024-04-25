package io.ay.bookstore.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ay.bookstore.exception.ValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class CustomLogger {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    public static void logInfo(String message) {
        logger.info("Message: {}", message);
    }

    public static void logError(Throwable throwable) {
        logger.error("Message: {}", throwable.getMessage());
        throwable.printStackTrace();
    }

    public static void logError(Errors errors) {
        if (errors.hasErrors()) throw new ValidationFailedException(errors.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; ")));
    }

    public static void logRequest(String methodName, Object request) {
        String content;
        try {
            content = mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            content = request.toString();
        }
        logger.info("{} [REQUEST] {} => {}", methodName, content, LocalDateTime.now());
    }

    public static void logResponse(String methodName, Object response) {
        String content;
        try {
            content = mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            content = response.toString();
        }
        logger.info("{} [RESPONSE] {} => {}", methodName, content, LocalDateTime.now());
    }
}
