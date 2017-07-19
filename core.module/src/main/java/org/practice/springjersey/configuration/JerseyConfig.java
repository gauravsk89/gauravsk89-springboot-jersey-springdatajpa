package org.practice.springjersey.configuration;

import lombok.extern.slf4j.Slf4j;
import org.glassfish.jersey.server.ResourceConfig;
import org.practice.springjersey.exception.exceptionmappers.ApiServiceExceptionMapper;
import org.practice.springjersey.exception.exceptionmappers.ThrowableMapper;
import org.practice.springjersey.exception.exceptionmappers.ValidationExceptionMapper;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/rest")
@Slf4j
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {

		packages("org.practice.springjersey.rest");

		// registering exception mappers
		register(ValidationExceptionMapper.class);
		register(ThrowableMapper.class);
		register(ApiServiceExceptionMapper.class);
	}

}
