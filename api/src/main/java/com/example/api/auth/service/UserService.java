package com.example.api.auth.service;

import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;

public interface UserService {
  Role saveRole(Role role);
  void signUpUser(User user);



  // void addRoleToUser(String username, Long roleId);

  // User saveUser(User user);

  // User getUser(String username);
  // List<User>getUsers();
}
