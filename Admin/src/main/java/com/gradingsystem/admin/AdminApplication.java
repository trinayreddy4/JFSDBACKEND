package com.gradingsystem.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@LoadBalancerClient(name = "Admin")
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
//		System.getProperty("user.dir");
	}
	
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	
	
}
