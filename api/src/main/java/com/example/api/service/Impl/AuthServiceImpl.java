package com.example.api.service.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.model.entity.User;
import com.example.api.model.form.SignUpForm;
import com.example.api.repo.UserRepo;
import com.example.api.service.AuthService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
  @Autowired
  private UserRepo userRepo;

  private final PasswordEncoder passwordEncoder;

  public String signUp(SignUpForm form) {
    User user = new User();
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setPassword(form.getPassword());
    // user.setRole(form.getRole());

    boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("The email address you entered is already in use");
    } else {
      user.setId(UUID.randomUUID().toString());
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepo.save(user);
    }
    return user.getId();
  }
}
