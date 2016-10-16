package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

//@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
//@EnableResourceServer
@RestController

public class ZuulGateway {


    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);


    @RequestMapping("/resource")
    public Map<String, Object> home() {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }


    public static void main(String[] args) {

        System.setProperty("spring.config.name", "zuul");
        Thread.currentThread().setName("ZuulGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }



}
