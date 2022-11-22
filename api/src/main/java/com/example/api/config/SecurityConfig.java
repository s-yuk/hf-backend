package com.example.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.api.services.auth.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private JwtUserDetailsService userDetailsService;

  @Value("${jwt.accesstoken.secretkey}")
  private String accessTokenSecret;

  public SecurityConfig(JwtUserDetailsService userService) {
      this.userDetailsService = userService;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
        .headers().frameOptions().sameOrigin()
        .and().csrf().disable();

      http
        .authorizeRequests()
        .antMatchers("/api/refreshToken", "/api/**").permitAll()
        .anyRequest().authenticated();

      http
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), userDetailsService))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), accessTokenSecret))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService)
              .passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
}
