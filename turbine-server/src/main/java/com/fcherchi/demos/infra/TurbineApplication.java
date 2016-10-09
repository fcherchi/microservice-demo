package com.fcherchi.demos.infra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * Created by Fernando Cherchi on 09/10/2016.
 */

@SpringBootApplication
@EnableTurbineStream
@EnableDiscoveryClient
public class TurbineApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "turbine-server");
        Thread.currentThread().setName("TurbineService-0");
        SpringApplication.run(TurbineApplication.class, args);
    }

}

