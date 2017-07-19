package org.practice.springjersey.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication(scanBasePackages = "org.practice.springjersey")
public class AppInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppInitializer.class);
    }

    public static void main(String[] args) throws Exception {
        log.debug("Starting up application");
        SpringApplication.run(AppInitializer.class, args);
    }
}
