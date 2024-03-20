package com.developer.pe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioZuulApplication.class, args);
	}

}
