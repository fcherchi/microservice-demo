package com.fcherchi.demos.application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistrationServer {


    final static Logger logger = (Logger) LoggerFactory.getLogger(RegistrationServer.class);

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for registration-server.properties or registration-server.yml
        System.setProperty("spring.config.name", "registration-server");
        Thread.currentThread().setName("RegistrationServer-0");
        logger.info("Starting Registration Server");
        SpringApplication.run(RegistrationServer.class, args);
    }

}