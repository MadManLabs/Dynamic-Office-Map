package com.endava.fsociety.dynamicofficemap.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by fstancu on 5/11/2016.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.endava.fsociety.dynamicofficemap.server"})
@EnableMongoRepositories(basePackages = "com.endava.fsociety.dynamicofficemap.server.repository")
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
