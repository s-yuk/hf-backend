package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.form.RegistrationForm;
import com.example.api.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public String register (@RequestBody RegistrationForm form) {
    authService.signUp(form);
    return "is work";
  }
}