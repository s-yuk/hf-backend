package com.example.api.config;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtUtils {
  // TODO decode token

  public String generateJwtToken(String id) {
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    String token = JWT.create()
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(id)
        .sign(algorithm);
    return token;
  }
}
