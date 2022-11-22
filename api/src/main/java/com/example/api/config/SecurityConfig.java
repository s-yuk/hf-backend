package com.example.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.cors().configurationSource(this.corsConfigurationSource());
    http.csrf().ignoringAntMatchers("/sample");

    http.authorizeHttpRequests()
      .antMatchers("/").permitAll();

      http.addFilter(new JsonAuthenticationFilter(authenticationManager()));
      http.csrf().ignoringAntMatchers("/api.**");
  }

  private CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    corsConfiguration.addExposedHeader("X-AUTH-TOKEN");
    corsConfiguration.addAllowedOrigin("http://localhost:5432");
    corsConfiguration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
    corsSource.registerCorsConfiguration("/**", corsConfiguration);
    return corsSource;
}
}
