package com.example.api.service.auth;

import java.util.List;

import com.example.api.model.auth.Role;
import com.example.api.model.auth.User;

public interface UserService {
  User saveUser(User user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  User getUser(String username);
  List<User>getUsers();
}
