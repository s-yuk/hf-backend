package com.example.api.service.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.model.Role;
import com.example.api.model.dto.SignUpDto;
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

  public SignUpDto signUp(SignUpForm form) {

    SignUpDto signUpDto = new SignUpDto();

    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setUsername(form.getUsername());
    user.setEmail(form.getEmail());
    user.setPassword(passwordEncoder.encode(form.getPassword()));

    // 親か子どもの判定
    String userType = form.getRole();
    switch (userType) {
      case "CHILD":
        user.setRole(Role.CHILD);
        break;
      case "PARENT":
        user.setRole(Role.PARENT);
        break;
    }
    boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("The email address you entered is already in use");
    }
    userRepo.save(user);

    // TODO 実装する
    String token = "token";

    signUpDto.setId(user.getId());
    signUpDto.setToken(token);
    return signUpDto;
  }
}
