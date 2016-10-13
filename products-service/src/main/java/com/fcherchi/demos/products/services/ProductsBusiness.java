package com.fcherchi.demos.products.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fcherchi.demos.products.model.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

/**
 * Created by Fernando Cherchi on 07/10/16.
 */


@RestController
public class ProductsBusiness {

    private static Logger LOGGER = LoggerFactory.getLogger(ProductsBusiness.class);

    @Autowired
    private LoadBalancerClient loadBalancer;

    private RestTemplate restTemplate = new RestTemplate();


    @RequestMapping("/")
    public String getProduct() {
        return "{\"timestamp\":\"" + new Date() + "\",\"content\":\"Hello from ProductAPi\"}";
    }



    @RequestMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable int productId,
                                              @RequestHeader(value="Authorization") String authorizationHeader,
                                              Principal currentUser) {

        LOGGER.debug("User {}", currentUser.getName());


        //this service (Product) which has a public API is consuming a core service (core)
        //which has a "private" RestAPI

        //first get the product information, this is mandatory and if we cannot get it we will return an error
        Product product = createProduct();

        // now, this can fail, we should implement a resilient system and a circuit breaker
        // the point is not breaking the flow if the optional stuff is not there
        ServiceInstance coreService = loadBalancer.choose("core-server");

        //could be null when offline
        if (coreService != null) {

            //this instrumentation should be in an utility class (just a jar)
            //we get the url to call the core service (let's imagine is a kvk caller that bring us extra info about the product)
            String url = coreService.getUri().toString() + "/core/" + productId;


            LOGGER.debug("Get xtra info from URL: {}", url);

            ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);

            //if kvk is up and running, complete information, if not leave it as null
            if (resultStr.getStatusCode().is2xxSuccessful()) {

                LOGGER.debug("Core addition body: {}", resultStr.getBody());
                product.setExtra(resultStr.getBody());
            }
        }


        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    private Product createProduct() {
        return new Product(1, "one");
    }


}
