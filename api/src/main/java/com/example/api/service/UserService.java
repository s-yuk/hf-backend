package com.example.api.service;

import java.util.List;
import java.util.Optional;

import com.example.api.model.User;

public interface UserService {
  List<User> getUsers();

  Optional<User> getUserById(Long id);

  User saveUser(User user);

  User updateUserById(User user);
}
