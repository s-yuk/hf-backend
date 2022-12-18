package com.example.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.api.filter.CustomAuthenticationFilter;
import com.example.api.service.Impl.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  //   auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  // }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
    // customAuthenticationFilter.setFilterProcessesUrl("/api/v1/login");
    http
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    /**
     * TODO
     * /api/v1/register/**, /api/v1/login/**, /api/token/refresh/**追加
     */
    http.authorizeHttpRequests(authz -> authz
          .mvcMatchers("/").permitAll()
          // .anyRequest().authenticated();
    );

    // http.addFilter(customAuthenticationFilter);
    // http.addFilterBefore(new CustomAuthorizationFilter(),
    //     UsernamePasswordAuthenticationFilter.class);

    http.cors();

    return http.build();
    }

  // @Bean
  // @Override
  // public AuthenticationManager authenticationManagerBean() throws Exception {
  //   return super.authenticationManagerBean();
  // }
}
