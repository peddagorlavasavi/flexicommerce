package com.scrotify.flexicommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vasavi 
 * RestConfig 
 * getRestTemplate() method has been created to create
 *         bean of RestTemplate
 */
public class RestConfig {
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
