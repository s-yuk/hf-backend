package com.example.api.config;

import com.example.api.dto.UserAuthentification;
import com.example.api.dto.UserIssueToken;
import com.example.api.services.auth.JwtUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JwtUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            final UserAuthentification user = new ObjectMapper().readValue(req.getInputStream(), UserAuthentification.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUserId(),
                            user.getPassword(),
                            Collections.emptyList())
            );
        } catch (IOException e) {
            throw new BadCredentialsException("Invalid credentials", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException {
        final UserIssueToken issueToken = userDetailsService.issueToken(auth.getName());
        final String json = (new ObjectMapper()).writeValueAsString(issueToken);

        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write(json);
        res.getWriter().flush();
    }
}