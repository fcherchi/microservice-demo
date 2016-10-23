package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

//@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
@RestController
@ImportAutoConfiguration(ZuulGateway.SecurityConfiguration.class)
public class ZuulGateway {


    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);


    public static void main(String[] args) {

        System.setProperty("spring.config.name", "zuul");
        Thread.currentThread().setName("ZuulGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }




    @RequestMapping("/resource")
    public Map<String, Object> home() {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }



    @Configuration
    //@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    @Order(value = 0)
    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
                    .anyRequest().authenticated();
        }
    }



}
