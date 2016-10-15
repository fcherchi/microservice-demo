package com.fcherchi.demos.products;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

@EnableOAuth2Sso
//@EnableOAuth2Client
@SpringBootApplication
@EnableEurekaClient
public class ProductsService extends WebSecurityConfigurerAdapter {

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

}
