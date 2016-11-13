package com.fcherchi.demos.infra;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class OauthServerApplication extends SpringBootServletInitializer {

	private static Logger logger = LoggerFactory.getLogger(OauthServerApplication.class);

	public static void main(String[] args) {

		System.setProperty("spring.config.name", "oauthserver");
		Thread.currentThread().setName("OAuthServer-0");
		logger.info("Starting OAuth Server");
		SpringApplication.run(OauthServerApplication.class, args);
	}
}
