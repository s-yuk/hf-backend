package com.example.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.api.auth.service.ResouceService;
import com.example.api.model.Role;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(ResouceService resouceService) {
		return args -> {
			resouceService.saveRole(new Role(null, "ROLE_USER"));
			resouceService.saveRole(new Role(null, "ROLE_MANAGER"));
			resouceService.saveRole(new Role(null, "ROLE_ADMIN"));
			resouceService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
		};
	}
}
