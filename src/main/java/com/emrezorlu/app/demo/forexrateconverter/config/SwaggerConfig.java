package com.emrezorlu.app.demo.forexrateconverter.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Forex Rate Converter API").description("Forex Rate Converter API")
				.version("v0.0.1").license(new License().name("Apache 2.0").url("http://emrezorlu.com")));
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("springshop-public").pathsToMatch("/**").build();
	}

}
