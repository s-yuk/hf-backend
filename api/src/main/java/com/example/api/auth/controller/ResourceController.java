package com.example.api.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.auth.service.ResouceService;
import com.example.api.model.Role;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ResourceController {
  private final ResouceService userService;

  @PostMapping("/role/save")
  public void saveRole(Role role) {
    userService.saveRole(role);
  }
}