package com.emrezorlu.app.demo.forexrateconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ForexrateconverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForexrateconverterApplication.class, args);
	}

}
