package com.example.api.auth.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.auth.service.AuthService;
import com.example.api.model.User;
import com.example.api.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepo _userRepo;

  public void signUpUser(User user) {
    String email = user.getEmail();
    String password = user.getPassword();
    boolean present = _userRepo.findByEmail(email).isPresent();
    if (!present) {
      user.setPassword(passwordEncoder.encode(password));
      _userRepo.save(user);
    } else {
      throw new IllegalStateException("The email address you entered is already in use");
    }
  }
}
