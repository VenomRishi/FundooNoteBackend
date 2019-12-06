package com.bridgelabz.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FundooDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundooDiscoveryServerApplication.class, args);
	}

}
