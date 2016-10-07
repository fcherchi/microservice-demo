package com.fcherchi.demos.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by Fernando Cherchi on 07/10/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class CoreServer {

    private static Logger LOGGER = LoggerFactory.getLogger(CoreServer.class);

    public static void main (String[] args) {

        System.setProperty("spring.config.name", "core-server");
        Thread.currentThread().setName("CoreServer-0");
        LOGGER.info("Starting Registration Server");
        SpringApplication.run(CoreServer.class, args);
    }
}
