package com.fcherchi.demos.core.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fernando Cherchi on 07/10/16.
 */
@RestController
public class ProductExtraInformationProvider {

    @RequestMapping("/core/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable int productId) {

        String extra =  "Extra Info for product: " + productId;
        return new ResponseEntity<>(extra, HttpStatus.OK);
    }

}
