package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.api.service.Impl.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  @Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailsService();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http.authorizeRequests().antMatchers("/").permitAll();

    http.antMatcher("/admin/**")
        .authorizeRequests().anyRequest().hasAuthority("ADMIN")
        .and()
        .formLogin()
          .loginPage("/admin/login")
          .usernameParameter("email")
          .loginProcessingUrl("/admin/login")
          .defaultSuccessUrl("/admin/home")
          .permitAll()
        .and()
        .logout()
          .logoutUrl("/admin/logout")
          .logoutSuccessUrl("/");

    return http.build();
    }
}
