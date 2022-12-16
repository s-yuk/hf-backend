package com.example.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.form.RegistrationForm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/register")
@AllArgsConstructor
@Slf4j
public class RegistrationController {

  @PostMapping
  public String register(RegistrationForm form) {
    log.info("form: {}", form);
    return "is work";
  }
}
