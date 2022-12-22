package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.form.SignUpForm;
import com.example.api.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public String register (@RequestBody SignUpForm form) {
    String id = authService.signUp(form);
    return id;
  }

  // login
  // logout
}
