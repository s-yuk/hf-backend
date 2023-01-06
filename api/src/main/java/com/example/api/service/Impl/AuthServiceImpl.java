package com.example.api.service.Impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.config.JwtUtils;
import com.example.api.model.Role;
import com.example.api.model.dto.LoginDto;
import com.example.api.model.entity.User;
import com.example.api.model.form.LoginForm;
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

    User userExists = userRepo.findByEmail(form.getEmail());
    if (userExists != null) {
      throw new IllegalStateException("The email address you entered is already in use");
    }

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
        user.setGroupId(user.getId());
        break;
    }

    userRepo.save(user);

    JwtUtils jwtUtils = new JwtUtils();
    String token = jwtUtils.generateJwtToken(user.getId());

    return token;
  }

  @Override
  public LoginDto login(LoginForm form) {
    LoginDto loginDto = new LoginDto();
    User user = userRepo.findByEmail(form.getEmail());
    String token = "";
    if (user != null) {
      boolean isMatchedPassword = isPasswordValid(form.getPassword(), user.getPassword());
      if (isMatchedPassword) {
        JwtUtils jwtUtils = new JwtUtils();
        token = jwtUtils.generateJwtToken(user.getId());
        Role role = user.getRole();
        loginDto.setToken(token);
        loginDto.setRole(role);
      }
    }

    return loginDto;
  }

  public boolean isPasswordValid(String plainPassword, String encodedPassword) {
    return passwordEncoder.matches(plainPassword, encodedPassword);
  }
}
