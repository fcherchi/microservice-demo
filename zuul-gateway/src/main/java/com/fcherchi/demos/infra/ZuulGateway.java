package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

//The OAuth2Sso includes the functionality of EnableOauth2Client plus the Authentication of a
//normal Spring application...
//It is in this example substituted by EnableOauthClient
//@EnableOAuth2Sso

@SpringBootApplication
@EnableZuulProxy
//@EnableOAuth2Client
//@RestController

//@Order(6)
public class ZuulGateway extends SpringBootServletInitializer {


    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);

    //part of the replacement of EnableOauth2Sso
//    @Autowired
//    private OAuth2ClientContext oauth2ClientContext;



    public static void main(String[] args) {

        System.setProperty("spring.config.name", "zuul");
        Thread.currentThread().setName("ZuulGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }


//    @Bean
//    //this maps to the yml (now not security.oauth2 anymore)
//    @ConfigurationProperties("facebook")
//    public ClientResources facebook() {
//        return new ClientResources();
//    }
//
//    @Bean
//    @ConfigurationProperties("github")
//    public ClientResources github() {
//        return new ClientResources();
//    }


    //this allows the redirection from our app to facebook (it is handled inside @EnableOAuth2Client
    //but this is to provide an order to the filter so it used before the main Spring Security Filter.
//    @Bean
//    public FilterRegistrationBean oauth2ClientFilterRegistration(
//            OAuth2ClientContextFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(filter);
//        registration.setOrder(-100);
//        return registration;
//    }



}



