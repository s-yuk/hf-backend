package com.example.api;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.api.model.auth.Role;
import com.example.api.model.auth.User;
import com.example.api.service.auth.UserService;


@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	// @Bean
	// PasswordEncoder passwordEncoder() {
	// 	return new BCryptPasswordEncoder();
	// }

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null, "john T", "john", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "will T", "will", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "alice T", "alice", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "hoge T", "hoge", "1234", new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("john", "ROLE_MANAGER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("alice", "ROLE_ADMIN");
			userService.addRoleToUser("hoge", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("hoge", "ROLE_ADMIN");
			userService.addRoleToUser("hoge", "ROLE_USER");
		};
	}
}
