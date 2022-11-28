package com.example.api.service.auth.Impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.model.auth.Role;
import com.example.api.model.auth.User;
import com.example.api.repo.auth.RoleRepo;
import com.example.api.repo.auth.UserRepo;
import com.example.api.service.auth.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service@RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  @Override
  public User saveUser(User user) {
    log.info("saving new user {} to the database", user.getName());
    return userRepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("adding role {} to user {}", roleName, username);
    User user = userRepo.findByUserName(username);
    Role role = roleRepo.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public User getUser(String username) {
    log.info("fetching user {}", username);
    return userRepo.findByUserName(username);
  }

  @Override
  public List<User> getUsers() {
    log.info("fetching all users");
    return userRepo.findAll();
  }

}
