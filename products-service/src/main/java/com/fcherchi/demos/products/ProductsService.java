package com.fcherchi.demos.products;


import com.fcherchi.demos.products.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


/**
 * Created by Fernando Cherchi on 02/10/16.
 */

@EnableAutoConfiguration
@Configuration
@EnableOAuth2Sso
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
    public String home(Principal user) {
        return "Hello " + user.getName() + " From Products Services";
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
