package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso

public class ZuulGateway extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index.html", "/home.html", "/")
                .permitAll().anyRequest().authenticated().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);


    public static void main(String[] args) {

        System.setProperty("spring.config.name", "zuul");
        Thread.currentThread().setName("ZuulGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }
}


//@EnableZuulProxy
//@SpringBootApplication
//@EnableEurekaClient
//@EnableResourceServer
//@RestController
//@EnableOAuth2Sso
//@ImportAutoConfiguration(ZuulGateway.SecurityConfiguration.class)
//public class ZuulGateway {
//
//
//    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);
//
//
//    public static void main(String[] args) {
//
//        System.setProperty("spring.config.name", "zuul");
//        Thread.currentThread().setName("ZuulGateway-0");
//        logger.info("Starting Api Gateway Zuul");
//        SpringApplication.run(ZuulGateway.class, args);
//    }
//
//
//
//
//    @RequestMapping("/resource")
//    public Map<String, Object> home() {
//
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("id", UUID.randomUUID().toString());
//        model.put("content", "Hello World");
//        return model;
//    }
//
//
//
//
//    @Configuration
//    //@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//    @Order(value = 0)
//    protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .httpBasic()
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll()
//                    .anyRequest().authenticated();
//        }
//    }
//}
