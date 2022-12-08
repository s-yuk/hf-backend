package com.example.api.auth.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.api.auth.model.Role;
import com.example.api.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {
  private final UserService userService;

  @PostMapping("/role/save")
  public ResponseEntity<Role>saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
    return ResponseEntity.created(uri).body(userService.saveRole(role));
  }


  // TODO refresh token

  // @GetMapping("/token/refresh")
  // public void refreshToken(User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
  //   String authorizationHeader = request.getHeader(AUTHORIZATION);
  //   if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
  //     try {
  //       String refresh_token = authorizationHeader.substring("Bearer ".length());
  //       Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
  //       JWTVerifier verifier = JWT.require(algorithm).build();
  //       DecodedJWT decodedJWT = verifier.verify(refresh_token);
  //       String email = decodedJWT.getSubject();
  //       user = userRepo.findUserEmail(email);
  //       String access_token = JWT.create()
  //         .withSubject(user.getEmail())
  //         .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
  //         .withIssuer(request.getRequestURL().toString())
  //         .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
  //         .sign(algorithm);
  //       Map<String, String> tokens = new HashMap<>();
  //       tokens.put("access_token", access_token);
  //       tokens.put("refresh_token", refresh_token);
  //       response.setContentType(APPLICATION_JSON_VALUE);
  //       new ObjectMapper().writeValue(response.getOutputStream(), tokens);
  //     } catch (Exception exception) {
  //       response.setHeader("error", exception.getMessage());
  //       response.setStatus(FORBIDDEN.value());
  //       Map<String, String> error = new HashMap<>();
  //       error.put("error_message", exception.getMessage());
  //       response.setContentType(APPLICATION_JSON_VALUE);
  //       new ObjectMapper().writeValue(response.getOutputStream(), error);
  //     }
  //   } else {
  //     throw new RuntimeException("refresh token is missing");
  //   }
  // }
}