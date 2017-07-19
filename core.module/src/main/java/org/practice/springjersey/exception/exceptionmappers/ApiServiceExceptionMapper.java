package org.practice.springjersey.exception.exceptionmappers;

import lombok.extern.slf4j.Slf4j;
import org.practice.springjersey.exception.ApiServiceException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class ApiServiceExceptionMapper implements ExceptionMapper<ApiServiceException>{

    @Override
    public Response toResponse(ApiServiceException e) {

        log.error("Exception received {}", e);

        return Response
                .status(e.getResponseCode())
                .entity(e.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
