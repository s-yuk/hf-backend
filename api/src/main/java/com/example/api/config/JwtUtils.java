package com.example.api.config;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

// generate token
// decode token
public class JwtUtils {
  public String generateJwtToken(String id) {
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    String token = JWT.create()
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
        .withIssuer(id)
        .sign(algorithm);
    return token;
  }

  public String decodeJwtToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(token);
    String id = decodedJWT.getIssuer();
    return id;
  }
}
