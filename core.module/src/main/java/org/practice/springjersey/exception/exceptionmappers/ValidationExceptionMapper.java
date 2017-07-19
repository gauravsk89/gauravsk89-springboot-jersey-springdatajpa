package org.practice.springjersey.exception.exceptionmappers;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
@Slf4j
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>{

    @Override
    public Response toResponse(ValidationException e) {

        log.error("Validation failed with exception {}", e.getMessage());

        final StringBuilder errorMessage = new StringBuilder();

        for (ConstraintViolation<?> cv : ((ConstraintViolationException) e).getConstraintViolations()) {
            errorMessage.append(cv.getMessage()+"\n");
        }

        return Response
                .status(BAD_REQUEST)
                .entity(errorMessage.toString())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
