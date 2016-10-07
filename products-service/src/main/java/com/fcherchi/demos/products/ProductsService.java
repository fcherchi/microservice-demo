package com.fcherchi.demos.products;


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
public class ProductsService {

    final static Logger logger = (Logger) LoggerFactory.getLogger(ProductsService.class);




    /**
     * Run the infra using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for registration-server.properties or registration-server.yml
        System.setProperty("spring.config.name", "products-service");
        Thread.currentThread().setName("ProductsService-0");
        logger.info("Starting Products Server");
        SpringApplication.run(ProductsService.class, args);
    }
}
