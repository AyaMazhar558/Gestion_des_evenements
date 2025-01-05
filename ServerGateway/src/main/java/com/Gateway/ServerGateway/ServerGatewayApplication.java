package com.Gateway.ServerGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class ServerGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerGatewayApplication.class, args);
	}
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						//cours
						.path("/api/users/**")
						.uri("http://localhost:8084/api/users")
				)
				.build();
	}

}