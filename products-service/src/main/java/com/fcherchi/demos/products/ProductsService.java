package com.fcherchi.demos.products;


import com.fcherchi.demos.products.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * Created by Fernando Cherchi on 02/10/16.
 */

@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@RestController
public class ProductsService {

    final static Logger logger = (Logger) LoggerFactory.getLogger(ProductsService.class);


    /**
     * Run the infra using Spring Boot and an embedded servlet engine.
     *
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for registration-server.properties or registration-server.yml
        System.setProperty("spring.config.name", "products-service");
        Thread.currentThread().setName("ProductsService-0");
        logger.info("Starting Products Server");
        SpringApplication.run(ProductsService.class, args);
    }

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }



    @RequestMapping("/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId) {

//                                                    ,
//                                              @RequestHeader(value="Authorization") String authorizationHeader,
//                                              Principal currentUser) {
        Product product = new Product();
        product.setName("Product " + productId);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
