package com.example.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.model.entity.User;
import com.example.api.model.form.RegistrationForm;
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

  public String signUp(RegistrationForm form) {
    User user = new User();
    user.setName(form.getName());
    user.setEmail(form.getEmail());
    user.setPassword(form.getPassword());

    boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("The email address you entered is already in use");
    } else {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepo.save(user);
    }
    return "service is work";
  }
}
