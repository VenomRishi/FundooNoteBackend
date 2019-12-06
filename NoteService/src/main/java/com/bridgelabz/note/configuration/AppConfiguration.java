/******************************************************************************
 *  Purpose: This is configuration class which holds all the configuration
 *  		 related application
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   24-10-2019
 *
 ******************************************************************************/

package com.bridgelabz.note.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfiguration {

	/**
	 * Purpose: this method is created for the functionality providing to
	 * application of ModelMapper this is used to map the simple POJO to @Entity
	 * class
	 * 
	 * @Bean annotation is used to getting the object bean at runtime
	 * 
	 * @return returns the object of ModelMapper class
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	
}
