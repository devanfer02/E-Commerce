package com.example.ecommerce;

import com.example.ecommerce.configs.ApiKeyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EcommerceApplication implements WebMvcConfigurer {

	@Autowired
	private ApiKeyInterceptor apiKeyInterceptor;
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiKeyInterceptor);
	}
}
