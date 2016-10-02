package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

@EnableZuulProxy
@SpringBootApplication
public class ZuulGateway {


    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);


    public static void main(String[] args) {

        System.setProperty("spring.config.name", "api-gateway");
        Thread.currentThread().setName("ApiGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }

}
