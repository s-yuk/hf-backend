package com.example.api.auth.service;

import java.util.List;

import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;

public interface UserService {
  Role saveRole(Role role);

  List<User> getUsers();

  User saveUser(User user);

  void updateUserPointById(Long id, User user);

  void signUpUser(User user);
}
