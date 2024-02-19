package dev.kilima.training.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {
	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r->r.path("/creditscoreapp/**")
				.uri("lb://creditscore-service"))
				
				.route(r1->r1.path("/loanapp/**")
				.uri("lb://loan-service"))
				.build();
	}
}
