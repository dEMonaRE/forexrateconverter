package com.emrezorlu.app.demo.forexrateconverter.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.emrezorlu.app.demo.forexrateconverter.common.constants.ApiPath;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(ApiPath.CONVERSION + "/**", ApiPath.FX_RATE + "/**",
				// -- Swagger UI v2
				"/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
				"/configuration/security", "/swagger-ui/index.html", "/webjars/**",
				// -- Swagger UI v3
				"/v3/api-docs/**", "/swagger-ui/**").permitAll().anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
