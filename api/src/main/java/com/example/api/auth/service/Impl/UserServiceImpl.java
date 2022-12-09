package com.example.api.auth.service.Impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;
import com.example.api.auth.repo.RoleRepo;
import com.example.api.auth.repo.UserRepo;
import com.example.api.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Role saveRole(Role role) {
    log.info("saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }

  @Override
  public List<User> getUsers() {
    return userRepo.findAll();
  }

  @Override
  public User saveUser(User user) {
    log.info("saving new user {} to the database", user);
    return userRepo.save(user);
  };

  @Override
  public void updateUserPointById(Long id, User user) {

  };

  public void signUpUser(User user) {
    boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("The email address you entered is already in use");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepo.findUserByEmail(email);
    if (user == null) {
      log.error("email not found in database: {}", email);
      throw new UsernameNotFoundException("email not found in database");
    } else {
      log.info("user found in database: {}", email);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return user;
  }
}