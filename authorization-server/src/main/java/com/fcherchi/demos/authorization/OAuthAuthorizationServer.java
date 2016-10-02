package com.fcherchi.demos.authorization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Created by Fernando Cherchi on 02/10/16.
 */
@SpringBootApplication
@EnableEurekaClient
public class OAuthAuthorizationServer {

    final static Logger logger = (Logger) LoggerFactory.getLogger(OAuthAuthorizationServer.class);

    /**
     * Run the infra using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for registration-server.properties or registration-server.yml
        System.setProperty("spring.config.name", "authorization-server");
        Thread.currentThread().setName("AuthorizationServer-0");
        logger.info("Starting OAuth Server");
        SpringApplication.run(OAuthAuthorizationServer.class, args);
    }
}
