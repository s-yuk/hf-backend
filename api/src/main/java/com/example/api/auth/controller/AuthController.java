package com.example.api.auth.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;
import com.example.api.auth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@RestController @RequestMapping(path = "/api") @AllArgsConstructor
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public void register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
    userService.signUpUser(user);
    // JWTtoken生成
    try {
      Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
      String access_token = JWT.create()
        .withSubject(user.getEmail())
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString())
        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
        .sign(algorithm);
      String refresh_token = JWT.create()
        .withSubject(user.getEmail())
        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString())
        .sign(algorithm);
      Map<String, String> tokens = new HashMap<>();
      tokens.put("access_token", access_token);
      tokens.put("refresh_token", refresh_token);
      response.setContentType(APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    } catch (Exception exception) {
      response.setHeader("error", exception.getMessage());
      response.setStatus(FORBIDDEN.value());
      Map<String, String> error = new HashMap<>();
      error.put("error_message", exception.getMessage());
      response.setContentType(APPLICATION_JSON_VALUE);
      new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
  }
}
