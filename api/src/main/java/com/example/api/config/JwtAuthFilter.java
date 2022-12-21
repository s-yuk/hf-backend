package com.example.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.springframework.http.HttpHeaders.*;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  // private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain) throws ServletException, IOException {
      final String authorizationHeader = request.getHeader(AUTHORIZATION);
      final String userEmail;
      final String jwtToken;


      if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer")) {
        filterChain.doFilter(request, response);
        return;
      }
      jwtToken = authorizationHeader.substring(7);
      userEmail = "userEmail"; // TODO

      // if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() = null) {
      //   UserDetails userDetails = userDetailsService
      // }
  }

}
