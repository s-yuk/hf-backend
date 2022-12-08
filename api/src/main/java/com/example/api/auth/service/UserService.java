package com.example.api.auth.service;

import java.util.Optional;

import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;

public interface UserService {
  static Optional<User> findUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }
  Role saveRole(Role role);
  void signUpUser(User user);
  User getUser(String username);
}
