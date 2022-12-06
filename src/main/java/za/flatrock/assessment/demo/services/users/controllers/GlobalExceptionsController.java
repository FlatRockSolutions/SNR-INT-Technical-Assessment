package za.flatrock.assessment.demo.services.users.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.flatrock.assessment.demo.models.dtos.UserAPIResponse;

@RestControllerAdvice
public class GlobalExceptionsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public UserAPIResponse userApiResponseExceptionHandler(Exception e) {
        logger.error("Exception while performing action", e);
        return new UserAPIResponse(500, HttpStatus.INTERNAL_SERVER_ERROR, "Unable to perform action",
                false, null);
    }
}
