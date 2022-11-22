package com.example.api.services.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.api.dto.UserIssueToken;
import com.example.api.repositories.UserRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.accesstoken.expirationtime}")
    private long accessTokenExpTime;

    @Value("${jwt.refreshtoken.expirationtime}")
    private long refreshTokenExpTime;

    @Value("${jwt.accesstoken.secretkey}")
    private String accessTokenSecret;

    private static final int REFRESH_TOKEN_LENGTH = 24;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JwtUserDetailsService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final com.example.api.models.User user = findByUsername(username);
        return User.withUsername(user.getUserId())
                        .password(user.getPassword())
                        .authorities(Collections.emptyList())
                        .build();
    }

    @Transactional
    public UserIssueToken issueToken(String username) throws UsernameNotFoundException {
        final Instant now = Instant.now();

        final String token = JWT.create()
                .withSubject(username)
                .withIssuedAt(Date.from(now))
                .withExpiresAt(Date.from(now.plus(accessTokenExpTime, ChronoUnit.SECONDS)))
                .sign(Algorithm.HMAC512(accessTokenSecret.getBytes()));

        return UserIssueToken.builder()
                .accessToken(token)
                .refreshToken(generateRefreshToken(username))
                .build();
    }

    @Transactional(readOnly = true)
    public boolean verifyRefreshToken(String username, String refreshToken) throws UsernameNotFoundException {
        final com.example.api.models.User user = findByUsername(username);

        if(user.getRefreshTokenIssuedAt() != null &&
                user.getRefreshTokenIssuedAt().plus(refreshTokenExpTime, ChronoUnit.SECONDS).isBefore(Instant.now())) {
            logger.info("Refresh token of {} is already expired", username);
            return false;
        }

        return StringUtils.isNotEmpty(user.getRefreshToken()) && passwordEncoder.matches(refreshToken, user.getRefreshToken());
    }

    private String generateRefreshToken(String username) throws UsernameNotFoundException {
        final com.example.api.models.User user = findByUsername(username);
        final String token = RandomStringUtils.randomAlphanumeric(REFRESH_TOKEN_LENGTH);
        user.setRefreshToken(passwordEncoder.encode(token));
        user.setRefreshTokenIssuedAt(Instant.now());
        userRepository.save(user);
        return token;
    }

    private com.example.api.models.User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found:[" + username + "]"));
    }
}