package com.example.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.form.LoginForm;
import com.example.api.model.form.SignUpForm;
import com.example.api.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j

// 新規登録
// ログイン
// ログアウト
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody SignUpForm form, HttpServletResponse response) {
    String token = authService.signUp(form);
    if (token.isEmpty()) {
      throw new IllegalArgumentException();
    }
    return ResponseEntity.ok(token);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginForm form, HttpServletResponse response) {
    LoginDto loginDto = new LoginDto();
    loginDto = authService.login(form);
    log.info("token", loginDto.getToken());
    if (loginDto.getToken().isEmpty()) {
      throw new IllegalArgumentException();
    }
    return ResponseEntity.ok(loginDto);
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
      if ("token".equals(cookie.getName())) {
        cookie.setValue(null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
      }
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
