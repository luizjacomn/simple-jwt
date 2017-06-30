package com.github.jjljrj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.github.jjljrj.filter.AuthenticationFilter;

@SpringBootApplication
public class SimpleJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleJwtApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean filtroJWT() {
		FilterRegistrationBean filtro = new FilterRegistrationBean();
		filtro.setFilter(new AuthenticationFilter());
		filtro.addUrlPatterns("/authenticated/*");
		return filtro;
	}
}