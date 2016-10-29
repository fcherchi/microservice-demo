package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fernando Cherchi on 02/10/16.
 */

//The OAuth2Sso includes the functionality of EnableOauth2Client plus the Authentication of a
//normal Spring application...
//It is in this example substituted by EnableOauthClient
//@EnableOAuth2Sso

@SpringBootApplication
//@EnableZuulProxy
@EnableOAuth2Client
@RestController

@EnableAuthorizationServer
@Order(6)
public class ZuulGateway extends WebSecurityConfigurerAdapter {


    final static Logger logger = (Logger) LoggerFactory.getLogger(ZuulGateway.class);

    //part of the replacement of EnableOauth2Sso
    @Autowired
    private OAuth2ClientContext oauth2ClientContext;



    public static void main(String[] args) {

        System.setProperty("spring.config.name", "zuul");
        Thread.currentThread().setName("ZuulGateway-0");
        logger.info("Starting Api Gateway Zuul");
        SpringApplication.run(ZuulGateway.class, args);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //part of the removal of EnableOAuth2Sso
                .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)

                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/webjars/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()

                //added by authorization server part
                .and().exceptionHandling()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))

                //provides a logout endpoint
                .and().logout().logoutSuccessUrl("/").permitAll()

                //During logout we need to validate the token (prevent CSRF). This is to be used in combination
                //with angular dealing with XSRF
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());


    }


    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();
        filters.add(ssoFilter(facebook(), "/login/facebook"));
        filters.add(ssoFilter(github(), "/login/github"));
        filter.setFilters(filters);
        return filter;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        filter.setRestTemplate(template);
        filter.setTokenServices(new UserInfoTokenServices(
                client.getResource().getUserInfoUri(), client.getClient().getClientId()));
        return filter;
    }

    @Bean
    //this maps to the yml (now not security.oauth2 anymore)
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("github")
    public ClientResources github() {
        return new ClientResources();
    }


    //this allows the redirection from our app to facebook (it is handled inside @EnableOAuth2Client
    //but this is to provide an order to the filter so it used before the main Spring Security Filter.
    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }


    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", principal.getName());
        return map;

    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration
            extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/me")
                    .authorizeRequests().anyRequest().authenticated();
        }
    }
}



