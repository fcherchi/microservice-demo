package com.fcherchi.demos.authorization;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


/**
 * Created by Fernando Cherchi on 02/10/16.
 */
@EnableEurekaClient
@SpringBootApplication
@EnableOAuth2Client
@RestController
//@EnableResourceServer
public class OAuthAuthorization extends WebSecurityConfigurerAdapter {

    final static Logger logger = (Logger) LoggerFactory.getLogger(OAuthAuthorization.class);

    /**
     * Run the infra using Spring Boot and an embedded servlet engine.
     *
     * @param args
     *            Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for authorization-server.properties or authorization-server.yml
        System.setProperty("spring.config.name", "authorization-server");
        Thread.currentThread().setName("AuthorizationServer-0");
        logger.info("Starting OAuth Server");
        SpringApplication.run(OAuthAuthorization.class, args);
    }

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
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

//    @Configuration
//    @EnableAuthorizationServer
//    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//        @Autowired
//        private AuthenticationManager authenticationManager;
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints.authenticationManager(authenticationManager);
//        }
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//            clients.inMemory()
//                    .withClient("acme")
//                    .secret("acmesecret")
//                    .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
//                    .scopes("webshop");
//        }
//    }
}
