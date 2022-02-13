package com.emrezorlu.app.demo.forexrateconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI().info(new Info().title("Forex Rate Converter API").description("Forex Rate Converter API")
				.version("v1.0").license(getLicense()).contact(getContact()));
	}

	public Contact getContact() {
		Contact contact = new Contact();
		contact.setEmail("ahmet@emrezorlu.com");
		contact.setName("Emre Zorlu");
		contact.setUrl("https://github.com/dEMonaRE");
		return contact;
	}

	public License getLicense() {
		License license = new License();
		license.setName("Apache License, Version 2.0");
		license.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
		return license;
	}

}
