package com.example.api.service;

import java.util.List;

import com.example.api.model.User;

public interface UserService {
  List<User> getUsers();

  User saveUser(User user);

  User updateUserById(User user);
}
