package com.example.api.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.api.UserForm;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  public JsonAuthenticationFilter(AuthenticationManager authenticationManager) {

      this.authenticationManager = authenticationManager;

      setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login","POST"));
      setUsernameParameter("username");
      setPasswordParameter("password");

      this.setAuthenticationSuccessHandler((req,res,ex) -> {
          String token = JWT.create()
                  .withIssuer("com.volkruss.toaru")
                  .withClaim("username", ex.getName())
                  .sign(Algorithm.HMAC256("secret"));
          res.setHeader("X-AUTH-TOKEN", token);
          res.setStatus(200);
      });

      this.setAuthenticationFailureHandler((req,res,ex) -> {
          res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      });
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      try {
          ServletInputStream stream = request.getInputStream();
          UserForm form = new ObjectMapper().readValue(request.getInputStream(), UserForm.class);
          return this.authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword(), new ArrayList<>())
          );
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
    }
  }
