package com.fcherchi.demos.authorization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * Created by Fernando Cherchi on 02/10/16.
 */
@SpringBootApplication
@RestController
@EnableAuthorizationServer
@EnableResourceServer
public class OAuthAuthorizationServer {

    final static Logger logger = (Logger) LoggerFactory.getLogger(OAuthAuthorizationServer.class);

    /**
     * Run the infra using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for registration-server.properties or registration-server.yml
        System.setProperty("spring.config.name", "authorization-server");
        Thread.currentThread().setName("AuthorizationServer-0");
        logger.info("Starting OAuth Server");
        SpringApplication.run(OAuthAuthorizationServer.class, args);
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

//    @RequestMapping(value="/", method = RequestMethod.GET)
//    public String hi(Principal p) {
//        return p!=null ? "Hello " + p.getName() : "Hello guest";
//    }

//    @Configuration
//    @EnableAuthorizationServer
//    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            clients.inMemory()
//                    .withClient("fernando")
//                    .secret("secret")
//                    .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
//                    .scopes("api");
//        }
//    }
}
