package org.practice.springjersey.exception.exceptionmappers;

import lombok.extern.slf4j.Slf4j;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

@Provider
@Slf4j
public class ThrowableMapper implements ExceptionMapper<Throwable>{


    @Override
    public Response toResponse(Throwable throwable) {

        log.error("Exception received {}", throwable);

        return Response
                .status(INTERNAL_SERVER_ERROR)
                .entity(throwable.getMessage())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();

    }
}
